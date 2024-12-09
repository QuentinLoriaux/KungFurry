package com.ensta.myfilmlist.dao;

import java.util.List;
import java.util.Optional;

import com.ensta.myfilmlist.model.Utilisateur;

public interface UtilisateurDAO {


    /**
     * Renvoie la liste de tous les films.
     *
     * @return la liste de tous les films
     */
    public List<Utilisateur> findAll();

    /**
     * Ajoute un film.
     *
     * @param film le film à ajouter
     * @return le film ajouté avec son id
     */
    public Utilisateur save(Utilisateur film);

    /**
     * Récupère un film par son id.
     * @param id l'id du film à récupérer
     * @return le film récupéré
     */
    public Optional<Utilisateur> findById(long id);

    /**
     * Supprime un film.
     * @param film le film à supprimer
     */
    public void delete(Utilisateur film);

    /**
     * Met à jour un film.
     * @param film le film à mettre à jour
     * @return le film mis à jour
     */
    public Utilisateur update(Utilisateur film);
}
