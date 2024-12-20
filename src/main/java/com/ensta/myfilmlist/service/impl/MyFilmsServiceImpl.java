package com.ensta.myfilmlist.service.impl;

import static java.lang.Math.pow;
import static java.lang.Math.round;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ensta.myfilmlist.dao.GenreDAO;
import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.dao.UtilisateurDAO;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.dto.UtilisateurDTO;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.form.FilmForm;
import com.ensta.myfilmlist.form.RealisateurForm;
import com.ensta.myfilmlist.form.UtilisateurForm;
import com.ensta.myfilmlist.mapper.FilmMapper;
import com.ensta.myfilmlist.mapper.RealisateurMapper;
import com.ensta.myfilmlist.mapper.UtilisateurMapper;
import com.ensta.myfilmlist.mapper.GenreMapper;

@Service
public class MyFilmsServiceImpl implements com.ensta.myfilmlist.service.MyFilmsService {

    private static final int NB_FILMS_MIN_REALISATEUR_CELEBRE = 3;

    @Autowired
    private FilmDAO filmDAO;
    @Autowired
    private RealisateurDAO realisateurDAO;
    @Autowired
    private UtilisateurDAO utilisateurDAO;
    @Autowired
    private GenreDAO genreDAO;

    public MyFilmsServiceImpl() {
    }

    @Override
    public Realisateur updateRealisateurCelebre(Realisateur realisateur) throws ServiceException{
        try {
            realisateur.setFilmRealises(this.filmDAO.findByRealisateurId(realisateur.getId()));
            realisateur.setCelebre(realisateur.getFilmRealises().size() >= NB_FILMS_MIN_REALISATEUR_CELEBRE);
            this.realisateurDAO.update(realisateur);
            return realisateur;
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour du réalisateur", e);
        }
    }

    @Override
    public int calculerDureeTotale(List<Film> films) {
        if (films == null) {
            return 0;
        }
        Stream<Film> filmStream = films.stream();
        return filmStream.mapToInt(Film::getDuree).sum();
    }

    @Override
    public double calculerNoteMoyenne(double[] notes){
        if (notes == null) {
            return 0;
        }
        double moyenne = Arrays.stream(notes).average().orElse(0.0);
        return round(moyenne * pow(10,2)) / pow(10,2);
    }

    @Override
    public List<Realisateur> updateRealisateurCelebres(List<Realisateur> realisateurs) throws ServiceException {
        try {
            return realisateurs.stream()
                    .filter(realisateur -> {
                        try {
                            return updateRealisateurCelebre(realisateur) != null;
                        } catch (ServiceException e) {
                            throw new RuntimeException("Erreur lors de la mise à jour des réalisateurs", e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour des réalisateurs", e);
        }
    }

    @Override
    public List<FilmDTO> findAllFilms() throws ServiceException {
        try {
            List<Film> films = this.filmDAO.findAll();
            return FilmMapper.convertFilmToFilmDTOs(films);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération des films", e);
        }
    }

    @Override
    public Page<FilmDTO> findAllFilms(int page, int size, String query, String sort, String order) throws ServiceException {
        try {
            Page<Film> films = this.filmDAO.findAll(page, size, query, sort, order);
            return new Page<>(films.getNumber(), films.getSize(), films.getTotal(), FilmMapper.convertFilmToFilmDTOs(films.getData()));
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération des films", e);
        }
    }

    @Override
    public FilmDTO createFilm(FilmForm form) throws ServiceException {
        try {
            Optional<Realisateur> realisateur = this.realisateurDAO.findById(form.getRealisateurId());
            if (realisateur.isEmpty()){
                throw new ServiceException("Le réalisateur n'existe pas");
            }
            Film film = FilmMapper.convertFilmFormToFilm(form);
            film.setGenre(this.genreDAO.getGenreById(form.getGenreId()).orElse(null));
            if (film.getGenre() == null){
                throw new ServiceException("Le genre n'existe pas");
            }
            film.setRealisateur(realisateur.get());
            film = this.filmDAO.save(film);
            this.updateRealisateurCelebre(realisateur.get());
            return FilmMapper.convertFilmToFilmDTO(film);
        } catch (Exception e) {
            throw new ServiceException("Impossible de créer le film : " + e.getMessage());
        }
    }

    @Override
    public List<RealisateurDTO> findAllRealisateurs() throws ServiceException {
        try {
            List<RealisateurDTO> realisateurDTOS = RealisateurMapper.convertRealisateurToRealisateurDTOs(this.realisateurDAO.findAll());
            for (RealisateurDTO realisateurDTO : realisateurDTOS) {
                realisateurDTO.setFilmRealises(FilmMapper.convertFilmToFilmDTOs(this.filmDAO.findByRealisateurId(realisateurDTO.getId())));
            }
            return realisateurDTOS;
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération des réalisateurs", e);
        }
    }

    @Override
    public Page<RealisateurDTO> findAllRealisateurs(int page, int size) throws ServiceException {
        try {
            Page<Realisateur> realisateurs = this.realisateurDAO.findAll(page, size);
            List<RealisateurDTO> realisateurDTOS = RealisateurMapper.convertRealisateurToRealisateurDTOs(realisateurs.getData());
            for (RealisateurDTO realisateurDTO : realisateurDTOS) {
                realisateurDTO.setFilmRealises(FilmMapper.convertFilmToFilmDTOs(this.filmDAO.findByRealisateurId(realisateurDTO.getId())));
            }
            return new Page<>(realisateurs.getNumber(), realisateurs.getSize(), realisateurs.getTotal(), realisateurDTOS);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération des réalisateurs", e);
        }
    }

    @Override
    public RealisateurDTO findRealisateurByNomAndPrenom(String nom, String prenom) throws ServiceException {
        try {
            Optional<Realisateur> realisateur = this.realisateurDAO.findByNomAndPrenom(nom, prenom);
            realisateur.ifPresent(value -> value.setFilmRealises(this.filmDAO.findByRealisateurId(value.getId())));
            return realisateur.map(RealisateurMapper::convertRealisateurToRealisateurDTO).orElse(null);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération du réalisateur", e);
        }
    }

    @Override
    public FilmDTO findFilmById(long id) throws ServiceException {
        try {
            Optional<Film> film = this.filmDAO.findById(id);

            return film.map(FilmMapper::convertFilmToFilmDTO).orElse(null);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération du film", e);
        }
    }

    @Override
    public void deleteFilm(long id) throws ServiceException {
        try {
            FilmDTO filmDTO = findFilmById(id);
            if (filmDTO == null) {
                throw new ServiceException("Le film n'existe pas");
            }
            this.filmDAO.delete(FilmMapper.convertFilmDTOToFilm(filmDTO));
            this.updateRealisateurCelebre(RealisateurMapper.convertRealisateurDTOToRealisateur(filmDTO.getRealisateur()));
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la suppression du film", e);
        }
    }

    @Override
    public RealisateurDTO findRealisateurById(long id) throws ServiceException {
        try {
            Optional<Realisateur> realisateur = this.realisateurDAO.findById(id);
            realisateur.ifPresent(value -> value.setFilmRealises(this.filmDAO.findByRealisateurId(id)));
            return realisateur.map(RealisateurMapper::convertRealisateurToRealisateurDTO).orElse(null);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération du réalisateur", e);
        }
    }

    @Override
    public RealisateurDTO createRealisateur(RealisateurForm realisateurForm) throws ServiceException {
        try {
            Realisateur realisateur = RealisateurMapper.convertRealisateurFormToRealisateur(realisateurForm);
            realisateur = this.realisateurDAO.save(realisateur);
            return RealisateurMapper.convertRealisateurToRealisateurDTO(realisateur);
        } catch (Exception e) {
            throw new ServiceException("Impossible de créer le réalisateur :" + e.getMessage());
        }
    }

    @Override
    public FilmDTO updateFilm(Film film) throws ServiceException {
        try {
            film = this.filmDAO.update(film);
            this.updateRealisateurCelebre(film.getRealisateur());
            return FilmMapper.convertFilmToFilmDTO(film);
        } catch (Exception e) {
            throw new ServiceException("Impossible de mettre à jour le film : " + e.getMessage());
        }
    }

    @Override
    public List<UtilisateurDTO> findAllUtilisateurs() throws ServiceException {
        try {
            return UtilisateurMapper.convertUtilisateurToUtilisateurDTOs(this.utilisateurDAO.findAll());
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération des réalisateurs", e);
        }
    }

    @Override
    public UtilisateurDTO findUtilisateurById(long id) throws ServiceException {
        try {
            Optional<Utilisateur> utilisateur = this.utilisateurDAO.findById(id);
            return utilisateur.map(UtilisateurMapper::convertUtilisateurToUtilisateurDTO).orElse(null);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération de l'utilisateur", e);
        }
    }

    @Override
    public UtilisateurDTO createUtilisateur(UtilisateurForm utilisateurForm) throws ServiceException {
        try {
            Utilisateur utilisateur = UtilisateurMapper.convertUtilisateurFormToUtilisateur(utilisateurForm);
            System.out.println(utilisateur.getId());
            utilisateur = this.utilisateurDAO.save(utilisateur);

            return UtilisateurMapper.convertUtilisateurToUtilisateurDTO(utilisateur);
        } catch (Exception e) {
            throw new ServiceException("Impossible de créer l'utilisateur :" + e.getMessage());
        }
    }

    @Override
    public void deleteUtilisateur(long id) throws ServiceException {
        try {
            UtilisateurDTO utilisateurDTO = findUtilisateurById(id);
            if (utilisateurDTO == null) {
                throw new ServiceException("Le utilisateur n'existe pas");
            }
            this.utilisateurDAO.delete(UtilisateurMapper.convertUtilisateurDTOToUtilisateur(utilisateurDTO));
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la suppression du utilisateur", e);
        }
    }

    @Override
    public UtilisateurDTO updateUtilisateur(Utilisateur utilisateur) throws ServiceException {
        try {
            utilisateur = this.utilisateurDAO.update(utilisateur);
            return UtilisateurMapper.convertUtilisateurToUtilisateurDTO(utilisateur);
        } catch (Exception e) {
            throw new ServiceException("Impossible de mettre à jour le utilisateur : " + e.getMessage());
        }
    }

    @Override
    public RealisateurDTO updateRealisateur(Realisateur realisateur) throws ServiceException {
        try {
            realisateur = this.realisateurDAO.update(realisateur);
            return RealisateurMapper.convertRealisateurToRealisateurDTO(realisateur);
        } catch (Exception e) {
            throw new ServiceException("Impossible de mettre à jour le réalisateur : " + e.getMessage());
        }
    }

    @Override
    public void deleteRealisateur(long id) throws ServiceException {
        try {
            this.realisateurDAO.delete(id);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la suppression du réalisateur", e);
        }
    }

    @Override
    public List<GenreDTO> findAllGenres() throws ServiceException {
        try {
            return GenreMapper.convertListGenreToListGenreDTO(this.genreDAO.getAllGenres());
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération des genres", e);
        }
    }

    @Override
    public GenreDTO findGenreById(long id) throws ServiceException {
        try {
            Optional<Genre> genre = this.genreDAO.getGenreById(id);
            return genre.map(GenreMapper::convertGenreToGenreDTO).orElse(null);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération du genre", e);
        }
    }




}