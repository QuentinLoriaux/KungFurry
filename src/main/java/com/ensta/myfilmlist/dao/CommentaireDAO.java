package com.ensta.myfilmlist.dao;

import java.util.List;
import java.util.Optional;

import com.ensta.myfilmlist.model.Commentaire;


public interface CommentaireDAO {

    /**
     * Renvoie la liste de tous les commentaires pour un film.
     *
     * @return la liste de tous les commentaires pour un film
     */
    public List<Commentaire> findByFilm(int filmId);

    /**
     * Ajoute un commentaire.
     *
     * @param commentaire le commentaire à ajouter
     * @return le commentaire ajouté avec son id
     */

    public Commentaire save(Commentaire commentaire);

    /**
     * Récupère un commentaire par son id.
     * @param id l'id du commentaire à récupérer
     * @return le commentaire récupéré
     */

    public Optional<Commentaire> findById(long id);

    /**
     * Supprime un commentaire.
     * @param commentaire le commentaire à supprimer
     */

    public void delete(long id);

    /**
     * Met à jour un commentaire.
     * @param commentaire le commentaire à mettre à jour
     * @return le commentaire mis à jour
     */

    public Commentaire edit(Commentaire commentaire);


}