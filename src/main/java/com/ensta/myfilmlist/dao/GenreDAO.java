package com.ensta.myfilmlist.dao;

import com.ensta.myfilmlist.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDAO {

    /**
     * Get a genre by its id
     * @param id the id of the genre
     * @return the genre
     */
    Optional<Genre> getGenreById(long id);

    /**
     * Get all genres
     * @return the list of genres
     */
    List<Genre> getAllGenres();
}
