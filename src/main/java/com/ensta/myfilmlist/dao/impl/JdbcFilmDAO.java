package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@Repository
public class JdbcFilmDAO implements com.ensta.myfilmlist.dao.FilmDAO {

    private JdbcTemplate jdbcTemplate = ConnectionManager.getJdbcTemplate();
    private RealisateurDAO RealisateurDAO = new JdbcRealisateurDAO();

    public JdbcFilmDAO() {
    }
    @Override
    public List<Film> findAll() {
        String query = "SELECT id, titre, duree, realisateur_id FROM film";

        return jdbcTemplate.query(query, (ResultSet rs, int rowNum) -> {
            Film film = new Film();
            film.setId(rs.getInt("id"));
            film.setTitre(rs.getString("titre"));
            film.setDuree(rs.getInt("duree"));
            film.setRealisateur(RealisateurDAO.findById(rs.getInt("realisateur_id")).orElse(null));
            return film;
        });
    }

    @Override
    public Film save(Film film){
        String CREATE_FILM_QUERY = "INSERT INTO film (titre, duree, realisateur_id) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = conn -> {
            PreparedStatement statement = conn.prepareStatement(
                    CREATE_FILM_QUERY,
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, film.getTitre());
            statement.setInt(2, film.getDuree());
            statement.setLong(3, film.getRealisateur().getId());
            return statement;
        };

        try {
            jdbcTemplate.update(creator, keyHolder);
            film.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
            return film;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'insertion du film", e);
        }
    }

    @Override
    public Optional<Film> findById(long id){
        String query = "SELECT id, titre, duree, realisateur_id FROM film WHERE id = ?";

        try {
            Film film = jdbcTemplate.queryForObject(query, new Object[]{id}, (ResultSet rs, int rowNum) -> {
                Film f = new Film();
                f.setId(rs.getInt("id"));
                f.setTitre(rs.getString("titre"));
                f.setDuree(rs.getInt("duree"));
                f.setRealisateur(RealisateurDAO.findById(rs.getInt("realisateur_id")).orElse(null));
                return f;
            });
            return Optional.ofNullable(film);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void delete(Film film){
        String DELETE_FILM_QUERY = "DELETE FROM film WHERE id = ?";

        try {
            jdbcTemplate.update(DELETE_FILM_QUERY, film.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du film", e);
        }
    }

    @Override
    public List<Film> findByRealisateurId(long realisateurId) {
        String query = "SELECT id, titre, duree, realisateur_id FROM film WHERE realisateur_id = ?";

        return jdbcTemplate.query(query, new Object[]{realisateurId}, (ResultSet rs, int rowNum) -> {
            Film film = new Film();
            film.setId(rs.getInt("id"));
            film.setTitre(rs.getString("titre"));
            film.setDuree(rs.getInt("duree"));
            film.setRealisateur(RealisateurDAO.findById(rs.getInt("realisateur_id")).orElse(null));
            return film;
        });
    }
}
