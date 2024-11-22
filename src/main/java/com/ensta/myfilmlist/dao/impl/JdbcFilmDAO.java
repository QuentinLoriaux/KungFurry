package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class JdbcFilmDAO implements com.ensta.myfilmlist.dao.FilmDAO {

    private JdbcTemplate jdbcTemplate = ConnectionManager.getJdbcTemplate();

    public JdbcFilmDAO() {
    }
    @Override
    public List<Film> findAll() {
        String query = "SELECT id, titre, duree FROM film";

        return jdbcTemplate.query(query, (ResultSet rs, int rowNum) -> {
            Film film = new Film();
            film.setId(rs.getInt("id"));
            film.setTitre(rs.getString("titre"));
            film.setDuree(rs.getInt("duree"));
            return film;
        });
    }
}
