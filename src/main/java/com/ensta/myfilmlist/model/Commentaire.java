package com.ensta.myfilmlist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@ManyToOne
	@JoinColumn(name = "film_id", nullable = false)
    private Film film;

	@ManyToOne
	@JoinColumn(name = "utilisateur_id", nullable = false)
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
}
