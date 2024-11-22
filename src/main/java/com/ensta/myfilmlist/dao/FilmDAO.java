package com.ensta.myfilmlist.dao;

import java.util.List;

import com.ensta.myfilmlist.dao.impl.JdbcFilmDAO;
import com.ensta.myfilmlist.model.Film;

public interface FilmDAO {


    /**
     * Renvoie la liste de tous les films.
     *
     * @return la liste de tous les films
     */
    public List<Film> findAll();

    /**
     * Ajoute un film.
     *
     * @param film le film à ajouter
     * @return le film ajouté avec son id
     */
    public Film save(Film film);
}
