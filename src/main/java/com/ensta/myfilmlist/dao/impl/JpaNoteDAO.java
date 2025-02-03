package com.ensta.myfilmlist.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ensta.myfilmlist.dao.NoteDAO;
import com.ensta.myfilmlist.model.Note;

@Repository
@Primary
public class JpaNoteDAO implements NoteDAO {
    @PersistenceContext


    private EntityManager entityManager;


    public List<Note> findByFilmId(long filmId) {
        return entityManager.createQuery("SELECT n FROM Note n WHERE n.film.id = :filmId", Note.class).setParameter("filmId", filmId).getResultList();
    }

    public Note save(Note note) {
        entityManager.persist(note);
        return note;
    }

    public Note findById(long id) {
        return entityManager.find(Note.class, id);
    }

    public void delete(Note note) {
        entityManager.remove(note);
    }

    public List<Note> findAll() {
        return entityManager.createQuery("SELECT n FROM Note n", Note.class).getResultList();
    }

    public Note update(Note note) {
        return entityManager.merge(note);
    }

    public Note getNote(long filmId, String username) {
        return entityManager.createQuery("SELECT n FROM Note n WHERE n.film.id = :filmId AND n.utilisateur.username = :username", Note.class).setParameter("filmId", filmId).setParameter("username", username).getSingleResult();
    }
}
