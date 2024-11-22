package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.persistence.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class JdbcFilmDAO implements com.ensta.myfilmlist.dao.FilmDAO {

    public JdbcFilmDAO() {
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();
        String query = "SELECT * FROM film";

        try (Connection conn = ConnectionManager.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Film film = new Film();
                film.setId(rs.getInt("id"));
                film.setTitre(rs.getString("titre"));
                film.setDuree(rs.getInt("duree"));
                films.add(film);
            }
        } catch (Exception e) {
            System.err.println("Error fetching films: " + e.getMessage());
        }
        return films;
    }
}
