package com.ensta.myfilmlist.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ensta.myfilmlist.model.Utilisateur;

@Repository
@Primary
public class JpaUtilisateurDAO implements com.ensta.myfilmlist.dao.UtilisateurDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Utilisateur> findAll() {
        return entityManager.createQuery("SELECT f FROM Utilisateur f", Utilisateur.class).getResultList();
    }

    public Utilisateur save(Utilisateur user) {
        System.out.println(user.getUsername());
        entityManager.persist(user);
        System.out.println("Here");
        return user;
    }

    public Optional<Utilisateur> findById(long id) {
        return Optional.ofNullable(entityManager.find(Utilisateur.class, id));
    }

    public void delete(Utilisateur user) {
        if (entityManager.find(Utilisateur.class, user.getId()) != null) {
            if (!entityManager.contains(user)) {
                user = entityManager.merge(user);
            }
            entityManager.remove(user);
        }
    }

    public Utilisateur update(Utilisateur user) {
        return entityManager.merge(user);
    }
}