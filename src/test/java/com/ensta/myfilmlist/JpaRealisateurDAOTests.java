package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.model.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaRealisateurDAOTest {

    @InjectMocks
    private JpaRealisateurDAO realisateurDAO;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Realisateur> query;

    @Mock
    private TypedQuery<Long> countQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Realisateur> mockRealisateurs = Arrays.asList(
                new Realisateur(1L, "Spielberg", "Steven", null, true),
                new Realisateur(2L, "Nolan", "Christopher", null, true)
        );

        when(entityManager.createQuery("SELECT r FROM Realisateur r", Realisateur.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(mockRealisateurs);

        // Act
        List<Realisateur> result = realisateurDAO.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Spielberg", result.get(0).getNom());
        assertEquals("Nolan", result.get(1).getNom());
        verify(entityManager).createQuery("SELECT r FROM Realisateur r", Realisateur.class);
        verify(query).getResultList();
    }

    @Test
    void testFindAllWithPagination() {
        // Arrange
        List<Realisateur> mockRealisateurs = Arrays.asList(
                new Realisateur(1L, "Spielberg", "Steven", null, true),
                new Realisateur(2L, "Nolan", "Christopher", null, true)
        );

        when(entityManager.createQuery("SELECT r FROM Realisateur r", Realisateur.class)).thenReturn(query);
        when(query.setFirstResult(0)).thenReturn(query);
        when(query.setMaxResults(2)).thenReturn(query);
        when(query.getResultList()).thenReturn(mockRealisateurs);

        when(entityManager.createQuery("SELECT COUNT(r) FROM Realisateur r", Long.class)).thenReturn(countQuery);
        when(countQuery.getSingleResult()).thenReturn(2L);

        // Act
        Page<Realisateur> result = realisateurDAO.findAll(1, 2);

        // Assert
        assertEquals(2, result.getData().size());
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getPage());
        assertEquals(2, result.getSize());
        verify(entityManager).createQuery("SELECT r FROM Realisateur r", Realisateur.class);
        verify(entityManager).createQuery("SELECT COUNT(r) FROM Realisateur r", Long.class);
        verify(query).getResultList();
        verify(countQuery).getSingleResult();
    }

    @Test
    void testFindById() {
        // Arrange
        Realisateur mockRealisateur = new Realisateur(1L, "Spielberg", "Steven", null, true);

        when(entityManager.find(Realisateur.class, 1L)).thenReturn(mockRealisateur);

        // Act
        Optional<Realisateur> result = realisateurDAO.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Spielberg", result.get().getNom());
        verify(entityManager).find(Realisateur.class, 1L);
    }

    @Test
    void testFindByNomAndPrenom() {
        // Arrange
        Realisateur mockRealisateur = new Realisateur(1L, "Spielberg", "Steven", null, true);

        when(entityManager.createQuery("SELECT r FROM Realisateur r WHERE r.nom = :nom AND r.prenom = :prenom", Realisateur.class)).thenReturn(query);
        when(query.setParameter("nom", "Spielberg")).thenReturn(query);
        when(query.setParameter("prenom", "Steven")).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(mockRealisateur));

        // Act
        Optional<Realisateur> result = realisateurDAO.findByNomAndPrenom("Spielberg", "Steven");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Spielberg", result.get().getNom());
        assertEquals("Steven", result.get().getPrenom());
        verify(entityManager).createQuery("SELECT r FROM Realisateur r WHERE r.nom = :nom AND r.prenom = :prenom", Realisateur.class);
        verify(query).getResultList();
    }

    @Test
    void testSave() {
        // Arrange
        Realisateur mockRealisateur = new Realisateur(1L, "Spielberg", "Steven", null, true);

        // Act
        realisateurDAO.save(mockRealisateur);

        // Assert
        verify(entityManager).persist(mockRealisateur);
    }

    @Test
    void testUpdate() {
        // Arrange
        Realisateur mockRealisateur = new Realisateur(1L, "Spielberg", "Steven", null, true);

        when(entityManager.merge(mockRealisateur)).thenReturn(mockRealisateur);

        // Act
        Realisateur result = realisateurDAO.update(mockRealisateur);

        // Assert
        assertEquals(mockRealisateur, result);
        verify(entityManager).merge(mockRealisateur);
    }

    @Test
    void testDelete() {
        // Arrange
        Realisateur mockRealisateur = new Realisateur(1L, "Spielberg", "Steven", null, true);

        when(entityManager.find(Realisateur.class, 1L)).thenReturn(mockRealisateur);

        // Act
        realisateurDAO.delete(1L);

        // Assert
        verify(entityManager).find(Realisateur.class, 1L);
        verify(entityManager).remove(mockRealisateur);
    }
}
