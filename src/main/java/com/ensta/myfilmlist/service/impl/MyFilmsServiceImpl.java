package com.ensta.myfilmlist.service.impl;

import static java.lang.Math.pow;
import static java.lang.Math.round;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.DatatypeConverter;

import com.ensta.myfilmlist.form.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensta.myfilmlist.dao.CommentaireDAO;
import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dao.GenreDAO;
import com.ensta.myfilmlist.dao.NoteDAO;
import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.dao.UtilisateurDAO;
import com.ensta.myfilmlist.dao.VueDAO;
import com.ensta.myfilmlist.dto.CommentaireDTO;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.FilmDetailsDTO;
import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.dto.NoteDTO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.dto.UtilisateurDTO;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.mapper.CommentaireMapper;
import com.ensta.myfilmlist.mapper.FilmMapper;
import com.ensta.myfilmlist.mapper.GenreMapper;
import com.ensta.myfilmlist.mapper.NoteMapper;
import com.ensta.myfilmlist.mapper.RealisateurMapper;
import com.ensta.myfilmlist.mapper.UtilisateurMapper;
import com.ensta.myfilmlist.model.Commentaire;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Genre;
import com.ensta.myfilmlist.model.Note;
import com.ensta.myfilmlist.model.Page;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.model.Utilisateur;

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
    @Autowired
    private NoteDAO noteDAO;
    @Autowired
    private VueDAO vueDAO;
    @Autowired
    private CommentaireDAO CommentaireDAO;

    private String tokenSecret = "FLAG{Th1s_1s_Th5_35cr5t_K5Y}";

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
    public double calculerNoteMoyenne(List<Note> notes){
        if (notes == null || notes.isEmpty()) {
            return 0;
        }
        double moyenne = notes.stream().mapToInt(Note::getNote).average().orElse(0);
        return round(moyenne * pow(10,2)) / pow(10,2);
    }

    @Override
    public Film updateNoteMoyenne(Film film) throws ServiceException {
        try {
            List<Note> notes = this.noteDAO.findByFilmId(film.getId());
            film.setNoteMoyenne(calculerNoteMoyenne(notes));
            return film;
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour du film", e);
        }
    }

    @Override
    public Film updateNbVues(Film film) throws ServiceException {
        try {
            film.setNbVues(this.vueDAO.findVuesByFilmId(film.getId()));
            return film;
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour du film", e);
        }
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
            return FilmMapper.convertFilmToFilmDTOs(this.filmDAO.findAll());
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
            throw new ServiceException("Erreur lors de la récupération des films"+ e.getMessage(), e);
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
            film.setNoteMoyenne(0);
            film.setNbVues(0);
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
    public FilmDetailsDTO findFilmById(long id) throws ServiceException {
        try {
            Optional<Film> film = this.filmDAO.findById(id);
            return film.map(FilmMapper::convertFilmToFilmDetailsDTO).orElse(null);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération du film", e);
        }
    }

    @Override
    public void deleteFilm(long id) throws ServiceException {
        try {
            FilmDetailsDTO filmDTO = findFilmById(id);
            if (filmDTO == null) {
                throw new ServiceException("Le film n'existe pas");
            }
            this.filmDAO.delete(FilmMapper.convertFilmDetailsDTOToFilm(filmDTO));
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
            film = this.updateNoteMoyenne(film);
            film = this.updateNbVues(film);
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
    public UtilisateurDTO findUtilisateurByUsername(String username) throws ServiceException {
        try {
            Optional<Utilisateur> utilisateur = this.utilisateurDAO.findByUsername(username);
            return utilisateur.map(UtilisateurMapper::convertUtilisateurToUtilisateurDTO).orElse(null);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération de l'utilisateur", e);
        }
    }

    @Override
    public UtilisateurDTO findUtilisateurByUsernamePassword(String username, String password) throws ServiceException {
        try {
            Optional<Utilisateur> utilisateur = this.utilisateurDAO.findByUsernamePassword(username, md5(password));
            return utilisateur.map(UtilisateurMapper::convertUtilisateurToUtilisateurDTO).orElse(null);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la récupération de l'utilisateur", e);
        }
    }

    @Override
    public UtilisateurDTO createUtilisateur(UtilisateurForm utilisateurForm) throws ServiceException {
        try {
            Utilisateur utilisateur = UtilisateurMapper.convertUtilisateurFormToUtilisateur(utilisateurForm);
            utilisateur = this.utilisateurDAO.save(utilisateur);

            return UtilisateurMapper.convertUtilisateurToUtilisateurDTO(utilisateur);
        } catch (Exception e) {
            throw new ServiceException("Impossible de créer l'utilisateur :" + e.getMessage());
        }
    }

    @Override
    public void deleteUtilisateur(String username) throws ServiceException {
        try {
            UtilisateurDTO utilisateurDTO = findUtilisateurByUsername(username);
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

    @Override
    public boolean checkToken(String token) throws ServiceException {
        String[] splited = token.split("\\.");
        return splited[1].equals(md5(this.tokenSecret + splited[0]));
    }

    @Override
    public String createToken(UtilisateurDTO userDTO) throws ServiceException {
        String to_sign = userDTO.getUsername() + ";" + String.valueOf(userDTO.getRoleId());
        return to_sign + "." + md5(this.tokenSecret + to_sign); 
    }


    @Override
    public String md5(String tohash) throws ServiceException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(tohash.getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Erreur lors du hash", e);
        }
    }

    @Override
    public CommentaireDTO addCommentaire(CommentaireForm commentaireForm, String username) throws ServiceException {
        try {
            Commentaire commentaire = CommentaireMapper.convertCommentaireFormToCommentaire(commentaireForm);
            commentaire.setDate(LocalDate.now());
            commentaire.setFilm(this.filmDAO.findById(commentaireForm.getFilmId()).orElse(null));
            commentaire.setUtilisateur(this.utilisateurDAO.findByUsername(username).orElse(null));
            commentaire = this.CommentaireDAO.save(commentaire);
            return CommentaireMapper.convertCommentaireToCommentaireDTO(commentaire);
        } catch (Exception e) {
            throw new ServiceException("Impossible d'ajouter le commentaire : " + e.getMessage());
        }
    }

    @Override
    public void deleteCommentaire(long id) throws ServiceException {
        try {
            System.out.println("id : " + id);
            this.CommentaireDAO.delete(id);
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la suppression du commentaire", e);
        }
    }

    @Override
    public CommentaireDTO editCommentaire(CommentaireForm commentaireForm, String username, long id) throws ServiceException {
        try {
            Commentaire commentaire = CommentaireMapper.convertCommentaireFormToCommentaire(commentaireForm);
            commentaire.setFilm(this.filmDAO.findById(commentaireForm.getFilmId()).orElse(null));
            commentaire.setUtilisateur(this.utilisateurDAO.findByUsername(username).orElse(null));
            commentaire.setDate(LocalDate.now());
            System.out.println("Commentaire modifié : " + commentaire.toString());
            commentaire = this.CommentaireDAO.edit(commentaire);
            return CommentaireMapper.convertCommentaireToCommentaireDTO(commentaire);
        } catch (Exception e) {
            throw new ServiceException("Impossible de mettre à jour le commentaire : " + e.getMessage());
        }
    }

    @Override
    public NoteDTO addNote(NoteForm noteForm, String username) throws ServiceException {
        try {
            Note note = NoteMapper.convertNoteFormToNote(noteForm);
            note.setFilm(this.filmDAO.findById(noteForm.getFilmId()).orElse(null));
            note.setUtilisateur(this.utilisateurDAO.findByUsername(username).orElse(null));
            note = this.noteDAO.save(note);
            updateNoteMoyenne(note.getFilm());
            System.out.println("Note ajoutée : " + note.toString());
            return NoteMapper.convertNoteToNoteDTO(note);
        } catch (Exception e) {
            throw new ServiceException("Impossible d'ajouter la note : " + e.getMessage());
        }
    }

    @Override
    public void deleteNote (long id,long filmId, String username ) throws ServiceException {
        try {
            Note note = noteDAO.getNote(id, username);
            System.out.println("Note supprimée : " + note.toString());
            this.noteDAO.delete(note);
            updateNoteMoyenne(this.filmDAO.findById(filmId).orElse(null));
        } catch (RuntimeException e) {
            throw new ServiceException("Erreur lors de la suppression de la note", e);
        }
    }

    @Override
    public NoteDTO editNote (NoteForm noteForm, String username, long id) throws ServiceException {
        try {
            Note note = NoteMapper.convertNoteFormToNote(noteForm);
            note.setFilm(filmDAO.findById(noteForm.getFilmId()).orElse(null));
            note.setUtilisateur(utilisateurDAO.findByUsername(username).orElse(null));
            System.out.println("Note modifiée : " + note.toString());
            note = this.noteDAO.update(note);
            System.out.println("Note modifiée : " + note.toString());
            updateNoteMoyenne(note.getFilm());
            return NoteMapper.convertNoteToNoteDTO(note);
        } catch (Exception e) {
            throw new ServiceException("Impossible de mettre à jour la note : " + e.getMessage());
        }
    }
    
    @Override
    public NoteDTO getNote (long filmId, String username) throws ServiceException {
        try {
            Note note = this.noteDAO.getNote(filmId, username);
            return NoteMapper.convertNoteToNoteDTO(note);
        } catch (Exception e) {
            throw new ServiceException("Impossible de récupérer la note : " + e.getMessage());
        }
    }

}