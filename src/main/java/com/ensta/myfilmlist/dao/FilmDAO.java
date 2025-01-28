package com.ensta.myfilmlist.dao;

import java.util.List;
import java.util.Optional;

import com.ensta.myfilmlist.model.Commentaire;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Page;

public interface FilmDAO {


    /**
     * Renvoie la liste de tous les films.
     *
     * @return la liste de tous les films
     */
    public List<Film> findAll();

    /**
     * Renvoie la liste de tous les films avec pagination.
     *
     * @param page le numéro de la page
     * @param size la taille de la page
     * @return la liste de tous les films de la page
     */
    public Page<Film> findAll(int page, int size, String query, String sort, String order);

    /**
     * Ajoute un film.
     *
     * @param film le film à ajouter
     * @return le film ajouté avec son id
     */
    public Film save(Film film);

    /**
     * Récupère un film par son id.
     * @param id l'id du film à récupérer
     * @return le film récupéré
     */
    public Optional<Film> findById(long id);

    /**
     * Supprime un film.
     * @param film le film à supprimer
     */
    public void delete(Film film);

    /**
     * Recherche les films par un réalisateur.
     * @param realisateurId l'id du réalisateur
     * @return la liste des films du réalisateur
     */
    public List<Film> findByRealisateurId(long realisateurId);

    /**
     * Met à jour un film.
     * @param film le film à mettre à jour
     * @return le film mis à jour
     */
    public Film update(Film film);

    /**
     * Renvoie la liste de tous les commentaires pour un film.
     * @param filmId l'id du film
     * @return la liste de tous les commentaires pour un film
     */
    public List<Commentaire> findCommentairesByFilmId(long filmId);
}
