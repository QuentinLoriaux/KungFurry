package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

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
}
