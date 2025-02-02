package com.ensta.myfilmlist.service;

import java.util.List;

import com.ensta.myfilmlist.dto.*;
import com.ensta.myfilmlist.model.*;
import org.springframework.transaction.annotation.Transactional;

import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.form.FilmForm;
import com.ensta.myfilmlist.form.RealisateurForm;
import com.ensta.myfilmlist.form.UtilisateurForm;

public interface MyFilmsService {
    /**
     * Met à jour le statut "celebre" du réalisateur en fonction du nombre de films réalisés.
     *
     * @param realisateur le réalisateur à mettre à jour, contenant la liste non nulle des films réalisés.
     * @return le réalisateur mis à jour avec son statut "celebre".
     * @throws ServiceException si une erreur survient pendant la mise à jour ou si le réalisateur est invalide.
     */
    Realisateur updateRealisateurCelebre(Realisateur realisateur) throws ServiceException;

    /**
     * Calcule la durée totale des films de la liste.
     *
     * @param films la liste des films à additionner.
     * @return la durée totale des films.
     */
    int calculerDureeTotale(List<Film> films);

    /**
     * Calcule la note moyenne des notes de la liste.
     *
     * @param notes la liste des notes à additionner.
     * @return la note moyenne des notes.
     */
    double calculerNoteMoyenne(List<Note> notes);

    /**
     * Met à jour la note moyenne d'un film.
     *
     * @param film le film à mettre à jour, contenant la liste non nulle des notes.
     * @return le film mis à jour avec sa note moyenne.
     * @throws ServiceException si une erreur survient pendant la mise à jour ou si le film est invalide.
     */
    Film updateNoteMoyenne(Film film) throws ServiceException;

    /**
     * Met à jour le nombre de vues d'un film.
     *
     * @param film le film à mettre à jour.
     * @return le film mis à jour avec son nombre de vues.
     * @throws ServiceException si une erreur survient pendant la mise à jour ou si le film est invalide.
     */
    Film updateNbVues(Film film) throws ServiceException;

    /**
     * Met à jour le statut "celebre" des réalisateurs en fonction du nombre de films réalisés.
     *
     * @param realisateurs la liste des réalisateurs à mettre à jour.
     * @return la liste des réalisateurs mis à jour avec leur statut "celebre".
     * @throws ServiceException si une erreur survient pendant la mise à jour ou si la liste des réalisateurs est invalide.
     */
    List<Realisateur> updateRealisateurCelebres(List<Realisateur> realisateurs) throws ServiceException;

    /**
     * Récupère la liste des films.
     *
     * @return la liste des films.
     * @throws ServiceException si une erreur survient pendant la récupération.
     */
    @Transactional
    List<FilmDTO> findAllFilms() throws ServiceException;

    /**
     * Récupère la liste des films.
     * @param page
     * @param size
     * @param query
     * @param sort
     * @param order
     * @return Page<FilmDTO>
     * @throws ServiceException
     */
    @Transactional
    Page<FilmDTO> findAllFilms(int page, int size, String query, String sort, String order) throws ServiceException;

    /**
     * Crée un film.
     * @param form le formulaire de création du film
     *
     * @return le film créé
     *
     */
    @Transactional
    FilmDTO createFilm(FilmForm form) throws ServiceException;

    /**
     * Récupère la liste des réalisateurs.
     * @return la liste des réalisateurs.
     */
    List<RealisateurDTO> findAllRealisateurs() throws ServiceException;

    /**
     * Récupère la liste des realisateurs.
     * @param page
     * @param size
     * @return Page<RealisateurDTO>
     * @throws ServiceException
     */
    Page<RealisateurDTO> findAllRealisateurs(int page, int size) throws ServiceException;

    /**
     * Récupère un réalisateurs par son nom et prénom.
     * @param nom
     * @param prenom
     * @return RealisateurDTO
     * @throws ServiceException
     */
    RealisateurDTO findRealisateurByNomAndPrenom(String nom, String prenom) throws ServiceException;

    /**
     * Récupère un film par son id.
     * @param id
     * @return FilmDetailsDTO
     * @throws ServiceException
     */
    @Transactional
    FilmDetailsDTO findFilmById(long id) throws ServiceException;

    /**
     * Supprime un film par son id.
     * @param id
     * @throws ServiceException
     */
    @Transactional
    void deleteFilm(long id) throws ServiceException;

    /**
     * Récupère un réalisateur par son id.
     * @param id
     * @return RealisateurDTO
     */
    RealisateurDTO findRealisateurById(long id) throws ServiceException;

    /**
     * Crée un réalisateur.
     * @param realisateurForm
     * @return realisateurDTO
     */
    @Transactional
    RealisateurDTO createRealisateur(RealisateurForm realisateurForm) throws ServiceException;

    /**
     * Met à jour un film.
     * @param film
     * @return filmDTO
     */
    @Transactional
    FilmDTO updateFilm(Film film) throws ServiceException;

    /**
     * Récupère la liste des utilisateurs.
     * @return la liste des utilisateurs.
     */
    List<UtilisateurDTO> findAllUtilisateurs() throws ServiceException;

    /**
     * Récupère un utilisateur par son id.
     * @param username l'identifiant du utilisateur à récupérer
     * @return UtilisateurDTO
     */
    UtilisateurDTO findUtilisateurByUsername(String username) throws ServiceException;

    /**
     * Récupère un utilisateur par son username/password.
     * @param username
     * @param password
     * @return UtilisateurDTO
     */
    UtilisateurDTO findUtilisateurByUsernamePassword(String username, String password) throws ServiceException;

    /**
     * Crée un utilisateur.
     * @param utilisateurForm
     * @return utilisateurDTO
     */

    @Transactional
    UtilisateurDTO createUtilisateur(UtilisateurForm utilisateurForm) throws ServiceException;

    /**
     * Supprime un utilisateur par son id.
     * @param username
     * @throws ServiceException
     */

    @Transactional
    void deleteUtilisateur(String username) throws ServiceException;

    /**
     * Met à jour un utilisateur.
     * @param utilisateur
     * @return utilisateurDTO
     */
    
    @Transactional
    UtilisateurDTO updateUtilisateur(Utilisateur utilisateur) throws ServiceException;

    /**
     * Met à jour un réalisateur.
     * @param realisateur
     * @return realisateurDTO
     */
    @Transactional
    RealisateurDTO updateRealisateur(Realisateur realisateur) throws ServiceException;

    /**
     * Supprime un réalisateur par son id.
     * @param id
     * @throws ServiceException
     */
    @Transactional
    void deleteRealisateur(long id) throws ServiceException;

    /**
     * Récupère la liste des Genres.
     * @return la liste des genres.
     */
    List<GenreDTO> findAllGenres() throws ServiceException;

    /**
     * Récupère un genre par son id.
     * @param id
     * @return GenreDTO
     */
    GenreDTO findGenreById(long id) throws ServiceException;


    /**
     * Vérifie la validité du token.
     * @param token
     * @return boolean
     */
    boolean checkToken(String token) throws ServiceException;

    /**
     * Créé le token d'un utilisateur.
     * @param userDTO
     * @return token
     */
    String createToken(UtilisateurDTO userDTO) throws ServiceException;

    public String md5(String tohash) throws ServiceException;

}
