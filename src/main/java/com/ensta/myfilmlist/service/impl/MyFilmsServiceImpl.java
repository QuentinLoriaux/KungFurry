package com.ensta.myfilmlist.service.impl;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.dao.impl.JdbcFilmDAO;
import com.ensta.myfilmlist.dao.impl.JdbcRealisateurDAO;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.form.FilmForm;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.mapper.FilmMapper;
import com.ensta.myfilmlist.mapper.RealisateurMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class MyFilmsServiceImpl implements com.ensta.myfilmlist.service.MyFilmsService {

    private static final int NB_FILMS_MIN_REALISATEUR_CELEBRE = 3;

    private FilmDAO filmDAO;
    private RealisateurDAO realisateurDAO;

    public MyFilmsServiceImpl() {
        this.filmDAO = new JdbcFilmDAO();
        this.realisateurDAO = new JdbcRealisateurDAO();
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
    public FilmDTO createFilm(FilmForm form) throws ServiceException {
        try {
            Optional<Realisateur> realisateur = this.realisateurDAO.findById(form.getRealisateurId());
            if (realisateur.isEmpty()){
                throw new ServiceException("Le réalisateur n'existe pas");
            }
            Film film = FilmMapper.convertFilmFormToFilm(form);
            film.setRealisateur(realisateur.get());
            film = this.filmDAO.save(film);
            this.updateRealisateurCelebre(realisateur.get());
            return FilmMapper.convertFilmToFilmDTO(film);
        } catch (Exception e) {
            throw new ServiceException("Impossible de récupérer le réalisateur :" + e.getMessage());
        }
    }

    @Override
    public List<RealisateurDTO> findAllRealisateurs() throws ServiceException {
        try {
            return RealisateurMapper.convertRealisateurToRealisateurDTOs(this.realisateurDAO.findAll());
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération des réalisateurs", e);
        }
    }

    @Override
    public RealisateurDTO findRealisateurByNomAndPrenom(String nom, String prenom) throws ServiceException {
        try {
            Optional<Realisateur> realisateur = this.realisateurDAO.findByNomAndPrenom(nom, prenom);
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
            return realisateur.map(RealisateurMapper::convertRealisateurToRealisateurDTO).orElse(null);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération du réalisateur", e);
        }
    }

    @Override
    public RealisateurDTO createRealisateur(RealisateurDTO realisateurDTO) throws ServiceException {
        try {
            Realisateur realisateur = RealisateurMapper.convertRealisateurDTOToRealisateur(realisateurDTO);
            realisateur = this.realisateurDAO.save(realisateur);
            return RealisateurMapper.convertRealisateurToRealisateurDTO(realisateur);
        } catch (Exception e) {
            throw new ServiceException("Impossible de créer le réalisateur :" + e.getMessage());
        }
    }
}