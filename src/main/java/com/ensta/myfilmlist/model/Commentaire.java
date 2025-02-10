package com.ensta.myfilmlist.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represente un commentaire fait par un utilisateur sur un film.
 */
@Entity
@Table(name = "commentaire")
public class Commentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String texte;

	@Column
	private LocalDate date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "film_id", nullable = false)
    private Film film;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
    private Utilisateur utilisateur;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return texte;
	}

	public void setText(String texte) {
		this.texte = texte;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

    public Film getFilm(){
        return film;
    }

    public void setFilm(Film film){
        this.film = film;
    }

    public Utilisateur getUtilisateur(){
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
    }

	@Override
	public String toString() {
		return "Commentaire{" +
				"id=" + id +
				", texte='" + texte + '\'' +
				", date=" + date +
				", film=" + film +
				", utilisateur=" + utilisateur +
				'}';
	}
}
