package com.ensta.myfilmlist.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ensta.myfilmlist.dto.*;
import com.ensta.myfilmlist.form.FilmForm;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Genre;
import com.ensta.myfilmlist.model.Realisateur;


/**
 * Effectue les conversions des Films entre les couches de l'application.
 */
public class FilmMapper {

	/**
	 * Convertit une liste de films en liste de DTO.
	 * 
	 * @param films la liste des films
	 * @return Une liste non nulle de dtos construite a partir de la liste des films.
	 */
	public static List<FilmDTO> convertFilmToFilmDTOs(List<Film> films) {
		return films.stream()
				.map(FilmMapper::convertFilmToFilmDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Convertit un film en DTO.
	 * 
	 * @param film le film a convertir
	 * @return Un DTO construit a partir des donnees du film.
	 */
	public static FilmDTO convertFilmToFilmDTO(Film film) {
		FilmDTO filmDTO = new FilmDTO();
		filmDTO.setId(film.getId());
		filmDTO.setTitre(film.getTitre());
		filmDTO.setDuree(film.getDuree());
		filmDTO.setAffiche(film.getAffiche());
		filmDTO.setNoteMoyenne(film.getNoteMoyenne());
		filmDTO.setNbVues(film.getNbVues());
		return filmDTO;
	}

	public static FilmDetailsDTO convertFilmToFilmDetailsDTO(Film film){
		FilmDetailsDTO filmDetailsDTO = new FilmDetailsDTO();
		filmDetailsDTO.setId(film.getId());
		filmDetailsDTO.setTitre(film.getTitre());
		filmDetailsDTO.setDuree(film.getDuree());
		Realisateur realisateur = film.getRealisateur();
		if (realisateur != null) {
			filmDetailsDTO.setRealisateur(RealisateurMapper.convertRealisateurToRealisateurDTO(realisateur));
		}
		Genre genre = film.getGenre();
		if (genre != null) {
			filmDetailsDTO.setGenre(GenreMapper.convertGenreToGenreDTO(genre));
		}
		filmDetailsDTO.setCommentaires(CommentaireMapper.convertCommentairesToCommentaireDTOs(film.getCommentaires()));
		filmDetailsDTO.setDescription(film.getSynopsis());
		filmDetailsDTO.setNoteMoyenne(film.getNoteMoyenne());
		filmDetailsDTO.setNbVues(film.getNbVues());
		filmDetailsDTO.setDateSortie(film.getDateSortie());
		filmDetailsDTO.setAffiche(film.getAffiche());
		return filmDetailsDTO;
	}

	/**
	 * Convertit un DTO en film.
	 * 
	 * @param filmDTO le DTO a convertir
	 * @return Un Film construit a partir des donnes du DTO.
	 */
	public static Film convertFilmDTOToFilm(FilmDTO filmDTO) {
		Film film = new Film();
		film.setId(filmDTO.getId());
		film.setTitre(filmDTO.getTitre());
		film.setDuree(filmDTO.getDuree());
		film.setAffiche(filmDTO.getAffiche());
		film.setNoteMoyenne(filmDTO.getNoteMoyenne());
		film.setNbVues(filmDTO.getNbVues());
		return film;
	}

	/**
	 * Convertit un Form en film.
	 * 
	 * @param filmForm le Form a convertir
	 * @return Un Film construit a partir des donnes du Form.
	 */
	public static Film convertFilmFormToFilm(FilmForm filmForm) {
		Film film = new Film();
		film.setTitre(filmForm.getTitre());
		film.setDuree(filmForm.getDuree());
		film.setDateSortie(filmForm.getDateSortie());
		film.setAffiche(filmForm.getAffiche());
		film.setSynopsis(filmForm.getSynopsis());
		return film;
	}

	/**
	 * Convertit une liste de DTO en liste de films.
	 *
	 * @param filmsDTO la liste des DTO
	 *                 @return Une liste non nulle de films construite à partir de la liste des DTO.
	 */
	public static List<Film> convertFilmsDTOToFilms(List<FilmDTO> filmsDTO) {
		return filmsDTO.stream()
				.map(FilmMapper::convertFilmDTOToFilm)
				.collect(Collectors.toList());
	}

	public static Film convertFilmDetailsDTOToFilm(FilmDetailsDTO filmDetailsDTO){
		Film film = new Film();
		film.setId(filmDetailsDTO.getId());
		film.setTitre(filmDetailsDTO.getTitre());
		film.setDuree(filmDetailsDTO.getDuree());
		film.setRealisateur(RealisateurMapper.convertRealisateurDTOToRealisateur(filmDetailsDTO.getRealisateur()));
		film.setGenre(GenreMapper.convertGenreDTOToGenre(filmDetailsDTO.getGenre()));
		film.setNoteMoyenne(filmDetailsDTO.getNoteMoyenne());
		film.setNbVues(filmDetailsDTO.getNbVues());
		film.setDateSortie(filmDetailsDTO.getDateSortie());
		film.setAffiche(filmDetailsDTO.getAffiche());
		film.setSynopsis(filmDetailsDTO.getDescription());
		return film;
	}
}
