package com.ensta.myfilmlist.dao;

import java.util.List;
import com.ensta.myfilmlist.model.Film;

public interface FilmDAO {
    /**
     * Renvoie la liste de tous les films.
     *
     * @return la liste de tous les films
     */
    public List<Film> findAll();
}
