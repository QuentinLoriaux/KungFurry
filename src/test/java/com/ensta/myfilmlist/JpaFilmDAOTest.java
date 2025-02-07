package com.ensta.myfilmlist;

import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Page;
import com.ensta.myfilmlist.dao.impl.JpaFilmDAO;
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

public class JpaFilmDAOTest {

   @Mock
   private EntityManager entityManager;

   @Mock
   private TypedQuery<Film> typedQuery;

   @Mock
   private TypedQuery<Long> countQuery;

   @InjectMocks
   private JpaFilmDAO jpaFilmDAO;

   @BeforeEach
   public void setUp() {
       MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testFindAll() {
       List<Film> films = Arrays.asList(new Film(), new Film());
       when(entityManager.createQuery("SELECT f FROM Film f", Film.class)).thenReturn(typedQuery);
       when(typedQuery.getResultList()).thenReturn(films);

       List<Film> result = jpaFilmDAO.findAll();

       assertEquals(films, result);
       verify(entityManager).createQuery("SELECT f FROM Film f", Film.class);
       verify(typedQuery).getResultList();
   }

   @Test
   public void testFindAllWithPaginationAndSorting() {
       List<Film> films = Arrays.asList(new Film(), new Film());
       when(entityManager.createQuery(anyString(), eq(Film.class))).thenReturn(typedQuery);
       when(entityManager.createQuery(anyString(), eq(Long.class))).thenReturn(countQuery);
       when(typedQuery.getResultList()).thenReturn(films);
       when(countQuery.getSingleResult()).thenReturn(2L);

       Page<Film> result = jpaFilmDAO.findAll(1, 2, "", "titre", "asc");

       assertNotNull(result);
    //    assertEquals(2, result.getTotalElements());     // does not exist
    //    assertEquals(films, result.getContent());
       verify(typedQuery).setFirstResult(0);
       verify(typedQuery).setMaxResults(2);
   }

   @Test
   public void testSave() {
       Film film = new Film();
       doNothing().when(entityManager).persist(film);

       Film result = jpaFilmDAO.save(film);

       assertEquals(film, result);
       verify(entityManager).persist(film);
   }

   @Test
   public void testFindById() {
       Film film = new Film();
       when(entityManager.find(Film.class, 1L)).thenReturn(film);

       Optional<Film> result = jpaFilmDAO.findById(1L);

       assertTrue(result.isPresent());
       assertEquals(film, result.get());
       verify(entityManager).find(Film.class, 1L);
   }

   @Test
   public void testFindByIdNotFound() {
       when(entityManager.find(Film.class, 1L)).thenReturn(null);

       Optional<Film> result = jpaFilmDAO.findById(1L);

       assertFalse(result.isPresent());
       verify(entityManager).find(Film.class, 1L);
   }

   @Test
   public void testDelete() {
       Film film = new Film();
       film.setId(1L);

       when(entityManager.find(Film.class, 1L)).thenReturn(film);
       doNothing().when(entityManager).remove(film);

       jpaFilmDAO.delete(film);

       verify(entityManager).find(Film.class, 1L);
       verify(entityManager).remove(film);
   }

   @Test
   public void testUpdate() {
       Film film = new Film();
       when(entityManager.merge(film)).thenReturn(film);

       Film result = jpaFilmDAO.update(film);

       assertEquals(film, result);
       verify(entityManager).merge(film);
   }

   @Test
   public void testFindByRealisateurId() {
       List<Film> films = Arrays.asList(new Film(), new Film());
       when(entityManager.createQuery("SELECT f FROM Film f WHERE f.realisateur.id = :realisateurId", Film.class)).thenReturn(typedQuery);
       when(typedQuery.setParameter("realisateurId", 1L)).thenReturn(typedQuery);
       when(typedQuery.getResultList()).thenReturn(films);

       List<Film> result = jpaFilmDAO.findByRealisateurId(1L);

       assertEquals(films, result);
       verify(typedQuery).setParameter("realisateurId", 1L);
       verify(typedQuery).getResultList();
   }
}
