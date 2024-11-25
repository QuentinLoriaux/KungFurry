package com.ensta.myfilmlist.service;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.form.FilmForm;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Realisateur;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    double calculerNoteMoyenne(double[] notes);

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
    List<FilmDTO> findAllFilms() throws ServiceException;

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
     * @return FilmDTO
     * @throws ServiceException
     */
    FilmDTO findFilmById(long id) throws ServiceException;

    /**
     * Supprime un film par son id.
     * @param id
     * @throws ServiceException
     */
    void deleteFilm(long id) throws ServiceException;

    /**
     * Récupère un réalisateur par son id.
     * @param id
     * @return RealisateurDTO
     */
    RealisateurDTO findRealisateurById(long id) throws ServiceException;

    /**
     * Crée un réalisateur.
     * @param realisateurDTO
     * @return realisateurDTO
     */
    RealisateurDTO createRealisateur(RealisateurDTO realisateurDTO) throws ServiceException;
}
