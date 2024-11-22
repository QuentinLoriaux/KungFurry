package com.ensta.myfilmlist.dao;

import java.util.List;
import java.util.Optional;

import com.ensta.myfilmlist.model.Film;

public interface FilmDAO {


    /**
     * Renvoie la liste de tous les films.
     *
     * @return la liste de tous les films
     */
    public List<Film> findAll();

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
}
