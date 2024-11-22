package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;


public class JdbcRealisateurDAO implements com.ensta.myfilmlist.dao.RealisateurDAO {

    private JdbcTemplate jdbcTemplate = ConnectionManager.getJdbcTemplate();

    public JdbcRealisateurDAO(){
    }

    @Override
    public List<Realisateur> findAll(){
        String query = "SELECT id, nom, prenom FROM realisateur";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Realisateur realisateur = new Realisateur();
            realisateur.setId(rs.getInt("id"));
            realisateur.setNom(rs.getString("nom"));
            realisateur.setPrenom(rs.getString("prenom"));

            return realisateur;
        });
    }

    @Override
    public Optional<Realisateur> findByNomAndPrenom(String nom, String prenom){
        String query = "SELECT id, nom, prenom, date_naissance FROM realisateur WHERE nom = ? AND prenom = ?";

        try {
            Realisateur realisateur = jdbcTemplate.queryForObject(query, new Object[]{nom, prenom}, (rs, rowNum) -> {
                Realisateur r = new Realisateur();
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setDateNaissance(rs.getDate("date_naissance").toLocalDate());
                return r;
            });
            return Optional.ofNullable(realisateur);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Realisateur> findById(long id){
        String query = "SELECT id, nom, prenom, date_naissance FROM realisateur WHERE id = ?";

        try {
            Realisateur realisateur = jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
                Realisateur r = new Realisateur();
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setDateNaissance(rs.getDate("date_naissance").toLocalDate());
                return r;
            });
            return Optional.ofNullable(realisateur);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
