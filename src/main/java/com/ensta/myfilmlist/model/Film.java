package com.ensta.myfilmlist.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represente un Film.
 */
@Entity
@Table(name = "film")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String titre;

	@Column
	private int duree;
	@ManyToOne
	@JoinColumn(name = "realisateur_id", nullable = false)
	private Realisateur realisateur;

	@ManyToOne
	@JoinColumn(name = "genre_id", nullable = false)
	private Genre genre;

	@Column(name = "note_moyenne")
	private double noteMoyenne;

	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
	private List<Note> notes;

	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
	private List<Commentaire> commentaires;

	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
	private List<Vue> vues;

	@Column(name = "nb_vues")
	private int nbVues;

	@Column(name = "date_sortie")
	private LocalDate dateSortie;

	@Column(name = "affiche")
	private String affiche;

	@Column(name = "synopsis")
	private String synopsis;

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

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Realisateur getRealisateur(){
		return realisateur;
	}

	public void setRealisateur(Realisateur realisateur){
		this.realisateur = realisateur;
	}

	public Genre getGenre(){
		return genre;
	}

	public void setGenre(Genre genre){
		this.genre = genre;
	}

    public double getNoteMoyenne() {
		return noteMoyenne;
	}

	public void setNoteMoyenne(double noteMoyenne) {
		this.noteMoyenne = noteMoyenne;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
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

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Vue> getVues() {
		return vues;
	}

	public void setVues(List<Vue> vues) {
		this.vues = vues;
	}
}
