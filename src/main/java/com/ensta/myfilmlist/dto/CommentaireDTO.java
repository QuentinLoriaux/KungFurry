package com.ensta.myfilmlist.dto;

import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Utilisateur;

/**
 * Contient les donnees d'un Commentaire.
 */

public class CommentaireDTO {
    private long id;
    private String text;
    private Film film;
    private Utilisateur utilisateur;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "CommentaireDTO [id=" + id + ", text=" + text + "]";
    }
}