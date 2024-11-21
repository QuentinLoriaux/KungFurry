package com.ensta.myfilmlist.service;

import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Realisateur;

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
    public int calculerDureeTotale(List<Film> films);

    /**
     * Calcule la note moyenne des notes de la liste.
     *
     * @param notes la liste des notes à additionner.
     * @return la note moyenne des notes.
     */
    public double calculerNoteMoyenne(double[] notes);

    /**
     * Met à jour le statut "celebre" des réalisateurs en fonction du nombre de films réalisés.
     *
     * @param realisateurs la liste des réalisateurs à mettre à jour.
     * @return la liste des réalisateurs mis à jour avec leur statut "celebre".
     * @throws ServiceException si une erreur survient pendant la mise à jour ou si la liste des réalisateurs est invalide.
     */
    List<Realisateur> updateRealisateurCelebres(List<Realisateur> realisateurs) throws ServiceException;
}
