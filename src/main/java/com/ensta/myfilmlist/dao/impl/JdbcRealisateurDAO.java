package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.model.Realisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JdbcRealisateurDAO implements com.ensta.myfilmlist.dao.RealisateurDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcRealisateurDAO(){
    }

    @Override
    public List<Realisateur> findAll(){
        String query = "SELECT id, nom, prenom, date_naissance, celebre  FROM realisateur";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Realisateur realisateur = new Realisateur();
            realisateur.setId(rs.getInt("id"));
            realisateur.setNom(rs.getString("nom"));
            realisateur.setPrenom(rs.getString("prenom"));
            realisateur.setDateNaissance(rs.getDate("date_naissance").toLocalDate());
            realisateur.setCelebre(rs.getBoolean("celebre"));
            return realisateur;
        });
    }

    @Override
    public Optional<Realisateur> findByNomAndPrenom(String nom, String prenom){
        String query = "SELECT id, nom, prenom, date_naissance, celebre FROM realisateur WHERE nom = ? AND prenom = ?";

        try {
            Realisateur realisateur = jdbcTemplate.queryForObject(query, new Object[]{nom, prenom}, (rs, rowNum) -> {
                Realisateur r = new Realisateur();
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setDateNaissance(rs.getDate("date_naissance").toLocalDate());
                r.setCelebre(rs.getBoolean("celebre"));
                return r;
            });
            return Optional.ofNullable(realisateur);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Realisateur> findById(long id){
        String query = "SELECT id, nom, prenom, date_naissance, celebre FROM realisateur WHERE id = ?";

        try {
            Realisateur realisateur = jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
                Realisateur r = new Realisateur();
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setDateNaissance(rs.getDate("date_naissance").toLocalDate());
                r.setCelebre(rs.getBoolean("celebre"));
                return r;
            });
            return Optional.ofNullable(realisateur);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Realisateur update(Realisateur realisateur){
        String query = "UPDATE realisateur SET nom = ?, prenom = ?, date_naissance = ?, celebre = ? WHERE id = ?";
        jdbcTemplate.update(query, realisateur.getNom(), realisateur.getPrenom(), realisateur.getDateNaissance(), realisateur.isCelebre(), realisateur.getId());
        return realisateur;
    }

    @Override
    public Realisateur save(Realisateur realisateur){
        String CREATE_REALISATEUR_QUERY = "INSERT INTO realisateur (nom, prenom, date_naissance, celebre) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = conn -> {
            PreparedStatement statement = conn.prepareStatement(
                    CREATE_REALISATEUR_QUERY,
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, realisateur.getNom());
            statement.setString(2, realisateur.getPrenom());
            statement.setDate(3, java.sql.Date.valueOf(realisateur.getDateNaissance()));
            statement.setBoolean(4, realisateur.isCelebre());
            return statement;
        };
        try {
            jdbcTemplate.update(creator, keyHolder);
            realisateur.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
            return realisateur;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'insertion du réalisateur", e);
        }
    }

    @Override
    public void delete(long id){
        String query = "DELETE FROM realisateur WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
