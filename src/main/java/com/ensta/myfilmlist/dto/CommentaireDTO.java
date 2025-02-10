package com.ensta.myfilmlist.dto;

import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Utilisateur;

import java.time.LocalDate;

/**
 * Contient les donnees d'un Commentaire.
 */
public class CommentaireDTO {
    private long id;
    private String text;
    private LocalDate date;
    private String username;

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

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CommentaireDTO [id=" + id + ", text=" + text + "]";
    }
}