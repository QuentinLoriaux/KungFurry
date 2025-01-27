//package com.ensta.myfilmlist.dao.impl;
//
//import com.ensta.myfilmlist.model.Genre;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//class JpaGenreDAOTest {
//
//    @InjectMocks
//    private JpaGenreDAO jpaGenreDAO;
//
//    @Mock
//    private EntityManager entityManager;
//
//    @Mock
//    private TypedQuery<Genre> typedQuery;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetGenreById_WhenGenreExists() {
//        Genre genre = new Genre();
//        genre.setId(1L);
//        genre.setName("Action");
//
//        when(entityManager.find(Genre.class, 1L)).thenReturn(genre);
//
//        Optional<Genre> result = jpaGenreDAO.getGenreById(1L);
//
//        assertTrue(result.isPresent());
//        assertEquals("Action", result.get().getName());
//        verify(entityManager, times(1)).find(Genre.class, 1L);
//    }
//
//    @Test
//    void testGetGenreById_WhenGenreDoesNotExist() {
//        when(entityManager.find(Genre.class, 1L)).thenReturn(null);
//
//        Optional<Genre> result = jpaGenreDAO.getGenreById(1L);
//
//        assertFalse(result.isPresent());
//        verify(entityManager, times(1)).find(Genre.class, 1L);
//    }
//
//    @Test
//    void testGetAllGenres() {
//        Genre genre1 = new Genre();
//        genre1.setId(1L);
//        genre1.setName("Action");
//
//        Genre genre2 = new Genre();
//        genre2.setId(2L);
//        genre2.setName("Comedy");
//
//        List<Genre> genres = Arrays.asList(genre1, genre2);
//
//        when(entityManager.createQuery("SELECT g FROM Genre g", Genre.class)).thenReturn(typedQuery);
//        when(typedQuery.getResultList()).thenReturn(genres);
//
//        List<Genre> result = jpaGenreDAO.getAllGenres();
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals("Action", result.get(0).getName());
//        assertEquals("Comedy", result.get(1).getName());
//        verify(entityManager, times(1)).createQuery("SELECT g FROM Genre g", Genre.class);
//        verify(typedQuery, times(1)).getResultList();
//    }
//}
