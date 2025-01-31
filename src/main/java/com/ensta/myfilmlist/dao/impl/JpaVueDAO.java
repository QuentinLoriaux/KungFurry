package com.ensta.myfilmlist.dao.impl;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaVueDAO implements com.ensta.myfilmlist.dao.VueDAO {
    private static final String FIND_VUES = "SELECT COUNT(*) FROM vue WHERE film_id = ?";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int findVuesByFilmId(long filmId) {
        return entityManager.createNativeQuery(FIND_VUES)
                .setParameter(1, filmId)
                .getFirstResult();
    }

    @Override
    public void addVue(long filmId, String username) {
        entityManager.createNativeQuery("INSERT INTO vue (film_id, username) VALUES (?, ?)")
            .setParameter(1, filmId)
            .setParameter(2, username)
            .executeUpdate();
    }

    @Override
    public void deleteVue(long filmId, String username) {
        entityManager.createNativeQuery("DELETE FROM vue WHERE film_id = ? AND username = ?")
            .setParameter(1, filmId)
            .setParameter(2, username)
            .executeUpdate();
    }
}
