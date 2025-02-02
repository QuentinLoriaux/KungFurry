package com.ensta.myfilmlist.dao;

import java.util.List;
import java.util.Optional;

import com.ensta.myfilmlist.model.Utilisateur;

public interface UtilisateurDAO {


    /**
     * Renvoie la liste de tous les utilisateurs.
     *
     * @return la liste de tous les utilisateurs
     */
    public List<Utilisateur> findAll();

    /**
     * Ajoute un utilisateur.
     *
     * @param utilisateur le utilisateur à ajouter
     * @return le utilisateur ajouté avec son id
     */
    public Utilisateur save(Utilisateur utilisateur);

    /**
     * Récupère un utilisateur par son username.
     * @param username le username du utilisateur à récupérer
     * @return le utilisateur récupéré
     */
    Optional<Utilisateur> findByUsername(String username);

    /**
     * Récupère un utilisateur par son username.
     * @param username le username du utilisateur à récupérer
     * @param password le password du utilisateur à récupérer
     *
     * @return le utilisateur récupéré
     */
    Optional<Utilisateur> findByUsernamePassword(String username, String password);

    /**
     * Supprime un utilisateur.
     * @param utilisateur le utilisateur à supprimer
     */
    public void delete(Utilisateur utilisateur);

    /**
     * Met à jour un utilisateur.
     * @param utilisateur le utilisateur à mettre à jour
     * @return le utilisateur mis à jour
     */
    public Utilisateur update(Utilisateur utilisateur);
}
