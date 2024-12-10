package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.model.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaGenreDAO implements com.ensta.myfilmlist.dao.GenreDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Genre> getGenreById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    public List<Genre> getAllGenres() {
        return entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }
}
