package com.ensta.myfilmlist.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ensta.myfilmlist.model.Commentaire;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Page;

@Repository
@Primary
public class JpaFilmDAO implements com.ensta.myfilmlist.dao.FilmDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Film> findAll() {
        return entityManager.createQuery("SELECT f FROM Film f", Film.class).getResultList();
    }

    public Page<Film> findAll(int page, int size, String query, String sort, String order) {
        StringBuilder jpql = new StringBuilder("SELECT f FROM Film f");
        StringBuilder countJpql = new StringBuilder("SELECT COUNT(f) FROM Film f");

        if (query != null && !query.isEmpty()) {
            String whereClause = " WHERE LOWER(f.titre) LIKE :query";
            jpql.append(whereClause);
            countJpql.append(whereClause);
        }

        if (sort != null && !sort.isEmpty()) {
            jpql.append(" ORDER BY f.").append(sort);
            if (order != null && order.equalsIgnoreCase("desc")) {
                jpql.append(" DESC");
            } else {
                jpql.append(" ASC");
            }
        }

        TypedQuery<Film> queryObj = entityManager.createQuery(jpql.toString(), Film.class)
                .setFirstResult((page -1) * size)
                .setMaxResults(size);
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql.toString(), Long.class);

        if (query != null && !query.isEmpty()) {
            queryObj.setParameter("query", "%" + query.toLowerCase() + "%");
            countQuery.setParameter("query", "%" + query.toLowerCase() + "%");
        }

        List<Film> films = queryObj.getResultList();
        long count = countQuery.getSingleResult();
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

    public List<Commentaire> findCommentairesByFilmId(long filmId) {
        return entityManager.createQuery("SELECT c FROM Commentaire c WHERE c.film.id = :filmId", Commentaire.class)
                .setParameter("filmId", filmId)
                .getResultList();
    }
}