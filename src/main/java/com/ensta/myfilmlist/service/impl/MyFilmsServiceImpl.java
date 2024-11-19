package com.ensta.myfilmlist.service.impl;

import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.model.Realisateur;

public class MyFilmsServiceImpl implements com.ensta.myfilmlist.service.MyFilmsService {

    private static final int NB_FILMS_MIN_REALISATEUR_CELEBRE = 3;

    public MyFilmsServiceImpl() {
    }

    @Override
    public Realisateur updateRealisateurCelebre(Realisateur realisateur) throws ServiceException{
        if (realisateur == null || realisateur.getFilmRealises() == null) {
            throw new ServiceException("Le réalisateur ou la liste de ses films est nulle.");
        }
        try {
            realisateur.setCelebre(realisateur.getFilmRealises().size() >= NB_FILMS_MIN_REALISATEUR_CELEBRE);
            return realisateur;
        } catch (Exception e) {
            throw new ServiceException("Erreur lors de la mise à jour du réalisateur", e);
        }
    }
}