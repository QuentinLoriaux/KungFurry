package com.ensta.myfilmlist.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Contient les donnees d'un Film.
 */
public class FilmDetailsDTO {

	private long id;
	private String titre;
	private String description;
	private int duree;
	private RealisateurDTO realisateur;
	private GenreDTO genre;
	private double noteMoyenne;
	private List<CommentaireDTO> commentaires;
	private int nbVues;
	private LocalDate dateSortie;
	private String affiche;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}

	public RealisateurDTO getRealisateur(){
		return realisateur;
	}
	public void setRealisateur(RealisateurDTO realisateur){
		this.realisateur = realisateur;
	}

	public GenreDTO getGenre(){
		return genre;
	}
	public void setGenre(GenreDTO genre){
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "FilmDTO [id=" + id + ", titre=" + titre + ", duree=" + duree + "]";
	}

	public double getNoteMoyenne() {
		return noteMoyenne;
	}
	public void setNoteMoyenne(double noteMoyenne) {
		this.noteMoyenne = noteMoyenne;
	}

	public List<CommentaireDTO> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(List<CommentaireDTO> commentaires) {
		this.commentaires = commentaires;
	}

	public int getNbVues() {
		return nbVues;
	}
	public void setNbVues(int nbVues) {
		this.nbVues = nbVues;
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

}
