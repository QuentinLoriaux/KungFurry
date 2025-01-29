package com.ensta.myfilmlist.model;

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

	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
	private List<Note> notes;

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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
