package com.ensta.myfilmlist.dao;

public interface VueDAO {
    /**
     * Récupère le nombre de vues d'un film.
     *
     * @param filmId l'identifiant du film.
     * @return le nombre de vues du film.
     */
    int findVuesByFilmId(long filmId);

    /**
     * Ajoute une vue à un film.
     *
     * @param filmId l'identifiant du film.
     * @param username le nom de l'utilisateur.
     *
     */
    void addVue(long filmId, String username);

    /**
     * Supprime une vue d'un film.
     *
     * @param filmId l'identifiant du film.
     * @param username le nom de l'utilisateur.
     *
     */
    void deleteVue(long filmId, String username);
}
