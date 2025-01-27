package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.model.Realisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class JdbcRealisateurDAOTest {

    @InjectMocks
    private JdbcRealisateurDAO jdbcRealisateurDAO;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Mock data
        Realisateur realisateur1 = new Realisateur(1, "Nolan", "Christopher", LocalDate.of(1970, 7, 30), true);
        Realisateur realisateur2 = new Realisateur(2, "Spielberg", "Steven", LocalDate.of(1946, 12, 18), true);

        when(jdbcTemplate.query(anyString(), any())).thenReturn(Arrays.asList(realisateur1, realisateur2));

        // Execute
        List<Realisateur> result = jdbcRealisateurDAO.findAll();

        // Verify
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Nolan", result.get(0).getNom());
        verify(jdbcTemplate, times(1)).query(anyString(), any());
    }

    @Test
    void testFindById() {
        // Mock data
        Realisateur realisateur = new Realisateur(1, "Nolan", "Christopher", LocalDate.of(1970, 7, 30), true);

        when(jdbcTemplate.queryForObject(anyString(), any(), eq(1L))).thenReturn(realisateur);

        // Execute
        Optional<Realisateur> result = jdbcRealisateurDAO.findById(1L);

        // Verify
        assertTrue(result.isPresent());
        assertEquals("Nolan", result.get().getNom());
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(), eq(1L));
    }

    @Test
    void testFindById_NotFound() {
        // Mock behavior
        when(jdbcTemplate.queryForObject(anyString(), any(), eq(1L)))
                .thenThrow(new EmptyResultDataAccessException(1));

        // Execute
        Optional<Realisateur> result = jdbcRealisateurDAO.findById(1L);

        // Verify
        assertFalse(result.isPresent());
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(), eq(1L));
    }

    @Test
    void testFindByNomAndPrenom() {
        // Mock data
        Realisateur realisateur = new Realisateur(1, "Nolan", "Christopher", LocalDate.of(1970, 7, 30), true);

        when(jdbcTemplate.queryForObject(anyString(), any(), eq("Nolan"), eq("Christopher")))
                .thenReturn(realisateur);

        // Execute
        Optional<Realisateur> result = jdbcRealisateurDAO.findByNomAndPrenom("Nolan", "Christopher");

        // Verify
        assertTrue(result.isPresent());
        assertEquals("Nolan", result.get().getNom());
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(), eq("Nolan"), eq("Christopher"));
    }

    @Test
    void testSave() {
        // Mock data
        Realisateur realisateur = new Realisateur(0, "Nolan", "Christopher", LocalDate.of(1970, 7, 30), true);

        KeyHolder keyHolder = mock(GeneratedKeyHolder.class);
        when(keyHolder.getKey()).thenReturn(1L);

        doAnswer(invocation -> {
            PreparedStatement ps = invocation.getArgument(0);
            return 1;
        }).when(jdbcTemplate).update(any(), eq(keyHolder));

        // Execute
        Realisateur savedRealisateur = jdbcRealisateurDAO.save(realisateur);

        // Verify
        assertNotNull(savedRealisateur);
        assertEquals(1, savedRealisateur.getId());
        verify(jdbcTemplate, times(1)).update(any(), eq(keyHolder));
    }

    @Test
    void testUpdate() {
        // Mock data
        Realisateur realisateur = new Realisateur(1, "Nolan", "Christopher", LocalDate.of(1970, 7, 30), true);

        // Execute
        Realisateur updatedRealisateur = jdbcRealisateurDAO.update(realisateur);

        // Verify
        assertNotNull(updatedRealisateur);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), any(), anyBoolean(), anyLong());
    }

    @Test
    void testDelete() {
        // Execute
        jdbcRealisateurDAO.delete(1L);

        // Verify
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1L));
    }
}
