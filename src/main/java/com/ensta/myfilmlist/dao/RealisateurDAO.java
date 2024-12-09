package com.ensta.myfilmlist.dao;

import com.ensta.myfilmlist.model.Realisateur;

import java.util.List;
import java.util.Optional;

public interface RealisateurDAO {
    /**
     * Récupère la liste des réalisateurs
     * @return la liste des réalisateurs
     */
    List<Realisateur> findAll();

    /**
     * Récupère un réalisateur par son nom et prénom
     * @param nom le nom du réalisateur
     * @param prenom le prénom du réalisateur
     * @return le réalisateur
     */
    Optional<Realisateur> findByNomAndPrenom(String nom, String prenom);

    /**
     * Récupère un réalisateur par son id
     * @param id l'id du réalisateur
     * @return le réalisateur
     */
    Optional<Realisateur> findById(long id);

    /**
     * Met à jour les informations d'un réalisateur
     * @param realisateur le réalisateur à mettre à jour
     * @return le réalisateur mis à jour
     */
    Realisateur update(Realisateur realisateur);

    /**
     * Ajoute un réalisateur
     * @param realisateur le réalisateur à ajouter
     * @return le réalisateur ajouté avec son id
     */
    Realisateur save(Realisateur realisateur);

    /**
     * Supprime un réalisateur
     * @param id l'id du réalisateur à supprimer
     * */
    void delete(long id);
}
