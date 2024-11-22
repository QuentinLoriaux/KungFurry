package com.ensta.myfilmlist.dao;

import com.ensta.myfilmlist.model.Realisateur;

import java.util.List;
import java.util.Optional;

public interface RealisateurDAO {
    /**
     * Récupère la liste des réalisateurs
     * @return la liste des réalisateurs
     */
    public List<Realisateur> findAll();

    /**
     * Récupère un réalisateur par son nom et prénom
     * @param nom le nom du réalisateur
     * @param prenom le prénom du réalisateur
     * @return le réalisateur
     */
    public Optional<Realisateur> findByNomAndPrenom(String nom, String prenom);

    /**
     * Récupère un réalisateur par son id
     * @param id l'id du réalisateur
     * @return le réalisateur
     */
    public Optional<Realisateur> findById(long id);
}
