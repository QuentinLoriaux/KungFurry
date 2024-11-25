package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.model.Realisateur;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JpaRealisateurDAO implements com.ensta.myfilmlist.dao.RealisateurDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Realisateur> findAll() {
        return entityManager.createQuery("SELECT r FROM Realisateur r", Realisateur.class).getResultList();
    }

    public Optional<Realisateur> findById(long id) {
        return Optional.ofNullable(entityManager.find(Realisateur.class, id));
    }

    public Optional<Realisateur> findByNomAndPrenom(String nom, String prenom) {
        return entityManager.createQuery("SELECT r FROM Realisateur r WHERE r.nom = :nom AND r.prenom = :prenom", Realisateur.class)
                .setParameter("nom", nom)
                .setParameter("prenom", prenom)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Realisateur update(Realisateur realisateur) {
        return entityManager.merge(realisateur);
    }

    public Realisateur save(Realisateur realisateur) {
        entityManager.persist(realisateur);
        return realisateur;
    }
}