// package com.ensta.myfilmlist;

// import com.ensta.myfilmlist.dao.GenreDAO;
// import com.ensta.myfilmlist.dao.RealisateurDAO;
// import com.ensta.myfilmlist.model.Film;
// import com.ensta.myfilmlist.model.Genre;
// import com.ensta.myfilmlist.model.Page;
// import com.ensta.myfilmlist.model.Realisateur;
// import com.ensta.myfilmlist.dao.impl.JdbcFilmDAO;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.dao.EmptyResultDataAccessException;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.support.GeneratedKeyHolder;
// import org.springframework.jdbc.support.KeyHolder;

// import java.sql.PreparedStatement;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;

// class JdbcFilmDAOTest {

//    @InjectMocks
//    private JdbcFilmDAO jdbcFilmDAO;

//    @Mock
//    private JdbcTemplate jdbcTemplate;

//    @Mock
//    private RealisateurDAO realisateurDAO;

//    @Mock
//    private GenreDAO genreDAO;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

//    @Test
//    void testFindAll() {
//        // Setup mock data
//        Realisateur realisateur = new Realisateur(1L, "Nolan");
//        Genre genre = new Genre(1L, "Action");
//        Film film = new Film(1, "Inception", 148, realisateur, genre);

//        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(Arrays.asList(film));
//        when(realisateurDAO.findById(1L)).thenReturn(Optional.of(realisateur));
//        when(genreDAO.getGenreById(1L)).thenReturn(Optional.of(genre));

//        // Execute
//        List<Film> films = jdbcFilmDAO.findAll();

//        // Verify
//        assertNotNull(films);
//        assertEquals(1, films.size());
//        assertEquals("Inception", films.get(0).getTitre());
//        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
//    }

//    @Test
//    void testFindById() {
//        // Setup mock data
//        Realisateur realisateur = new Realisateur(1L, "Nolan");
//        Genre genre = new Genre(1L, "Action");
//        Film film = new Film(1, "Inception", 148, realisateur, genre);

//        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyLong())).thenReturn(film);
//        when(realisateurDAO.findById(1L)).thenReturn(Optional.of(realisateur));
//        when(genreDAO.getGenreById(1L)).thenReturn(Optional.of(genre));

//        // Execute
//        Optional<Film> result = jdbcFilmDAO.findById(1L);

//        // Verify
//        assertTrue(result.isPresent());
//        assertEquals("Inception", result.get().getTitre());
//        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(RowMapper.class), anyLong());
//    }

//    @Test
//    void testFindById_NotFound() {
//        // Setup
//        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyLong()))
//                .thenThrow(new EmptyResultDataAccessException(1));

//        // Execute
//        Optional<Film> result = jdbcFilmDAO.findById(1L);

//        // Verify
//        assertFalse(result.isPresent());
//        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(), anyLong());
//    }

//    @Test
//    void testSave() {
//        // Setup mock data
//        Realisateur realisateur = new Realisateur(1L, "Nolan");
//        Genre genre = new Genre(1L, "Action");
//        Film film = new Film(0, "Inception", 148, realisateur, genre);

//        KeyHolder keyHolder = mock(GeneratedKeyHolder.class);
//        when(keyHolder.getKey()).thenReturn(1L);
//        doAnswer(invocation -> {
//            PreparedStatement ps = invocation.getArgument(0);
//            return 1;
//        }).when(jdbcTemplate).update(any(), eq(keyHolder));

//        // Execute
//        Film savedFilm = jdbcFilmDAO.save(film);

//        // Verify
//        assertNotNull(savedFilm);
//        assertEquals(1, savedFilm.getId());
//        verify(jdbcTemplate, times(1)).update(any(), eq(keyHolder));
//    }

//    @Test
//    void testDelete() {
//        // Setup mock data
//        Film film = new Film(1, "Inception", 148, null, null);

//        // Execute
//        jdbcFilmDAO.delete(film);

//        // Verify
//        verify(jdbcTemplate, times(1)).update(anyString(), eq(film.getId()));
//    }

//    @Test
//    void testFindAllWithPagination() {
//        // Setup mock data
//        Realisateur realisateur = new Realisateur(1L, "Nolan");
//        Genre genre = new Genre(1L, "Action");
//        Film film = new Film(1, "Inception", 148, realisateur, genre);

//        when(jdbcTemplate.query(anyString(), any(RowMapper.class), anyInt(), anyInt())).thenReturn(Arrays.asList(film));
//        when(jdbcTemplate.queryForObject(anyString(), eq(Long.class))).thenReturn(1L);
//        when(realisateurDAO.findById(1L)).thenReturn(Optional.of(realisateur));
//        when(genreDAO.getGenreById(1L)).thenReturn(Optional.of(genre));

//        // Execute
//        Page<Film> result = jdbcFilmDAO.findAll(1, 10, null, null, null);

//        // Verify
//        assertNotNull(result);
//        assertEquals(1, result.getTotalElements().intValue());
//        assertEquals(1, result.getContent().size().intValue());
//        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class), anyInt(), anyInt());
//        verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Long.class));
//    }

//    @Test
//    void testUpdate() {
//        // Setup mock data
//        Realisateur realisateur = new Realisateur(1L, "Nolan");
//        Genre genre = new Genre(1L, "Action");
//        Film film = new Film(1, "Inception", 148, realisateur, genre);

//        // Execute
//        Film updatedFilm = jdbcFilmDAO.update(film);

//        // Verify
//        assertNotNull(updatedFilm);
//        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyInt(), anyLong(), anyLong(), anyLong());
//    }
// }
