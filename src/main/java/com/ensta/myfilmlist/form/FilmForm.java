package com.ensta.myfilmlist.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * Contient les donnees pour requeter un film.
 */
public class FilmForm {

	@NotBlank
	private String titre;

	@Positive
	private int duree;

	@Positive
	private long realisateurId;

	@Positive
	private long genreId;

	@Past
	private LocalDate dateSortie;

	private String affiche;

	private String synopsis;

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public long getRealisateurId() {
		return realisateurId;
	}

	public void setRealisateurId(long realisateurId) {
		this.realisateurId = realisateurId;
	}

	public long getGenreId() {
		return genreId;
	}

	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}

	public LocalDate getDateSortie() {
		return dateSortie;
	}
	
	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}

	public String getAffiche() {
		return affiche;
	}

	public void setAffiche(String affiche) {
		this.affiche = affiche;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

}
