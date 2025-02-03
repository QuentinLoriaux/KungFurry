package com.ensta.myfilmlist.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ensta.myfilmlist.dao.CommentaireDAO;
import com.ensta.myfilmlist.model.Commentaire;

@Repository
@Primary
public class JpaCommentaireDAO implements CommentaireDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Commentaire> findByFilm(int filmId) {
        TypedQuery<Commentaire> query = entityManager.createQuery("SELECT c FROM Commentaire c WHERE c.film.id = :filmId", Commentaire.class);
        query.setParameter("filmId", filmId);
        return query.getResultList();
    }
    @Override
    public Commentaire save(Commentaire commentaire) {
        entityManager.persist(commentaire);
        return commentaire;
    }

    @Override
    public Commentaire findById(long id) {
        return entityManager.find(Commentaire.class, id);
    }

    @Override
    public void delete(Commentaire commentaire) {
        entityManager.remove(commentaire);
    }

    @Override
    public Commentaire edit(Commentaire commentaire) {
        entityManager.merge(commentaire);
        return commentaire;
    }
}