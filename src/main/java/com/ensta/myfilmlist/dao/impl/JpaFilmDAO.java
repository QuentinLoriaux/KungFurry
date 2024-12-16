package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Page;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JpaFilmDAO implements com.ensta.myfilmlist.dao.FilmDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Film> findAll() {
        return entityManager.createQuery("SELECT f FROM Film f", Film.class).getResultList();
    }

    public Page<Film> findAll(int page, int size) {
        List<Film> films = entityManager.createQuery("SELECT f FROM Film f", Film.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
        long count = entityManager.createQuery("SELECT COUNT(f) FROM Film f", Long.class).getSingleResult();
        return new Page<>(page, size, count, films);
    }

    public Film save(Film film) {
        entityManager.persist(film);
        return film;
    }

    public Optional<Film> findById(long id) {
        return Optional.ofNullable(entityManager.find(Film.class, id));
    }

    public void delete(Film film) {
        if (entityManager.find(Film.class, film.getId()) != null) {
            if (!entityManager.contains(film)) {
                film = entityManager.merge(film);
            }
            entityManager.remove(film);
        }
    }

    public List<Film> findByRealisateurId(long realisateurId) {
        return entityManager.createQuery("SELECT f FROM Film f WHERE f.realisateur.id = :realisateurId", Film.class)
                .setParameter("realisateurId", realisateurId)
                .getResultList();
    }

    public Film update(Film film) {
        return entityManager.merge(film);
    }
}