package com.ensta.myfilmlist.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ensta.myfilmlist.dao.GenreDAO;
import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.model.Commentaire;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Page;

@Repository
public class JdbcFilmDAO implements com.ensta.myfilmlist.dao.FilmDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RealisateurDAO RealisateurDAO;
    @Autowired
    private GenreDAO GenreDAO;

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
            film.setGenre(GenreDAO.getGenreById(rs.getInt("genre_id")).orElse(null));
            return film;
        });
    }

    @Override
    public Film save(Film film){
        String CREATE_FILM_QUERY = "INSERT INTO film (titre, duree, realisateur_id, genre_id) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = conn -> {
            PreparedStatement statement = conn.prepareStatement(
                    CREATE_FILM_QUERY,
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, film.getTitre());
            statement.setInt(2, film.getDuree());
            statement.setLong(3, film.getRealisateur().getId());
            statement.setLong(4, film.getGenre().getId());
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
            // https://www.baeldung.com/spring-boot-replace-deprecated-jdbctemplate-queryforobject-query
            // @SuppressWarnings("deprecation")
            // Film film = jdbcTemplate.queryForObject(query, new Object[]{id}, (ResultSet rs, int rowNum) -> {
            //     Film f = new Film();
            //     f.setId(rs.getInt("id"));
            //     f.setTitre(rs.getString("titre"));
            //     f.setDuree(rs.getInt("duree"));
            //     f.setRealisateur(RealisateurDAO.findById(rs.getInt("realisateur_id")).orElse(null));
            //     return f;
            // });
            
            Film film = jdbcTemplate.queryForObject(query, (ResultSet rs, int rowNum) -> {
                Film f = new Film();
                f.setId(rs.getInt("id"));
                f.setTitre(rs.getString("titre"));
                f.setDuree(rs.getInt("duree"));
                f.setGenre(GenreDAO.getGenreById(rs.getInt("genre_id")).orElse(null));
                f.setRealisateur(RealisateurDAO.findById(rs.getInt("realisateur_id")).orElse(null));
                return f;
            }, id);
            return Optional.ofNullable(film);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Page<Film> findAll(int page, int size, String query2, String sort, String order){
        String query = "SELECT id, titre, duree, realisateur_id FROM film LIMIT ? OFFSET ?";

        List<Film> films = jdbcTemplate.query(query, (ResultSet rs, int rowNum) -> {
            Film film = new Film();
            film.setId(rs.getInt("id"));
            film.setTitre(rs.getString("titre"));
            film.setDuree(rs.getInt("duree"));
            film.setRealisateur(RealisateurDAO.findById(rs.getInt("realisateur_id")).orElse(null));
            film.setGenre(GenreDAO.getGenreById(rs.getInt("genre_id")).orElse(null));
            return film;
        }, size, (page - 1) * size);

        String countQuery = "SELECT COUNT(id) FROM film";
        long count = jdbcTemplate.queryForObject(countQuery, Long.class);

        return new Page<>(page, size, count, films);
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

        // return jdbcTemplate.query(query, new Object[]{realisateurId}, (ResultSet rs, int rowNum) -> {
        //     Film film = new Film();
        //     film.setId(rs.getInt("id"));
        //     film.setTitre(rs.getString("titre"));
        //     film.setDuree(rs.getInt("duree"));
        //     film.setRealisateur(RealisateurDAO.findById(rs.getInt("realisateur_id")).orElse(null));
        //     return film;
        // });

        return jdbcTemplate.query(query, (ResultSet rs, int rowNum) -> {
            Film film = new Film();
            film.setId(rs.getInt("id"));
            film.setTitre(rs.getString("titre"));
            film.setDuree(rs.getInt("duree"));
            film.setRealisateur(RealisateurDAO.findById(rs.getInt("realisateur_id")).orElse(null));
            film.setGenre(GenreDAO.getGenreById(rs.getInt("genre_id")).orElse(null));
            return film;
        }, realisateurId);
    }

    @Override
    public Film update(Film film){
        String UPDATE_FILM_QUERY = "UPDATE film SET titre = ?, duree = ?, realisateur_id = ?, genre_id = ? WHERE id = ?";

        try {
            jdbcTemplate.update(UPDATE_FILM_QUERY, film.getTitre(), film.getDuree(), film.getRealisateur().getId(), film.getGenre().getId(), film.getId());
            return film;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour du film", e);
        }
    }

//    @Override
//    public List<Commentaire> findCommentairesByFilmId(long filmId){
//        String query = "SELECT id, text, film FROM commentaire WHERE film_id = ?";
//        return jdbcTemplate.query(query, (ResultSet rs, int rowNum) -> {
//            Commentaire commentaire = new Commentaire();
//            commentaire.setId(rs.getInt("id"));
//            commentaire.setText(rs.getString("text"));
//            return commentaire;
//        }, filmId);
//    }

}
