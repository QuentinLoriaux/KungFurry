package com.ensta.myfilmlist.dto;

import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Utilisateur;

/**
 * Contient les donnees d'une Note.
 */

public class NoteDTO {
    private long id;
    private int value;
    private Film film;
    private Utilisateur utilisateur;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int note) {
        this.value = note;
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
        return "NoteDTO [id=" + id + ", value=" + value + "]";
    }
}