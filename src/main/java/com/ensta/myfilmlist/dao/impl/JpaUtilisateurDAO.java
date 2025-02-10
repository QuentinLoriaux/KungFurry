package com.ensta.myfilmlist.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.ensta.myfilmlist.model.Utilisateur;

@Repository
@Primary
public class JpaUtilisateurDAO implements com.ensta.myfilmlist.dao.UtilisateurDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override    
    public List<Utilisateur> findAll() {
        return entityManager.createQuery("SELECT f FROM Utilisateur f", Utilisateur.class).getResultList();
    }

    

    @Override    
    public Optional<Utilisateur> findByUsernamePassword(String username, String passwordhash) {
        TypedQuery<Utilisateur> queryObj = entityManager.createQuery("SELECT f FROM Utilisateur f WHERE username = :username AND md5hex = :password", Utilisateur.class);
        queryObj.setParameter("username", username.toLowerCase());
        queryObj.setParameter("password", passwordhash);
        System.out.println();
        try {
            return Optional.ofNullable(queryObj.getSingleResult());
        } catch (NoResultException e) {
            return Optional.ofNullable(null);
        }
        
    }

    @Override
    public Utilisateur save(Utilisateur user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<Utilisateur> findByUsername(String username) {
        return Optional.ofNullable(entityManager.find(Utilisateur.class, username));
    }

    @Override
    public void delete(Utilisateur user) {
        if (entityManager.find(Utilisateur.class, user.getUsername()) != null) {
            if (!entityManager.contains(user)) {
                user = entityManager.merge(user);
            }
            entityManager.remove(user);
        }
    }

    @Override
    public Utilisateur update(Utilisateur user) {
        return entityManager.merge(user);
    }
}