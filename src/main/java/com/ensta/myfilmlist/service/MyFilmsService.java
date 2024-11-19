package com.ensta.myfilmlist.service;

import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.model.Realisateur;

public interface MyFilmsService {
    /**
     * Met à jour le statut "celebre" du réalisateur en fonction du nombre de films réalisés.
     *
     * @param realisateur le réalisateur à mettre à jour, contenant la liste non nulle des films réalisés.
     * @return le réalisateur mis à jour avec son statut "celebre".
     * @throws ServiceException si une erreur survient pendant la mise à jour ou si le réalisateur est invalide.
     */
    Realisateur updateRealisateurCelebre(Realisateur realisateur) throws ServiceException;
}
