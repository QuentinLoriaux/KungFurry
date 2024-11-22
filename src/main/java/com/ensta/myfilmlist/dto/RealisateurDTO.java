package com.ensta.myfilmlist.dto;

import java.time.LocalDate;
import java.util.List;

public class RealisateurDTO {
    private long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private List<FilmDTO> filmRealises;
    private Boolean celebre;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getPrenom(){
        return prenom;
    }
    public void setDateNaissance(LocalDate dateNaissance){
        this.dateNaissance = dateNaissance;
    }
    public LocalDate getDateNaissance(){
        return dateNaissance;
    }
    public void setFilmRealises(List<FilmDTO> filmRealises){
        this.filmRealises = filmRealises;
    }
    public List<FilmDTO> getFilmRealises(){
        return filmRealises;
    }
    public void setCelebre(Boolean celebre){
        this.celebre = celebre;
    }
    public Boolean getCelebre(){
        return celebre;
    }

    @Override
    public String toString(){
        return "RealisateurDTO [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", filmRealises=" + filmRealises + ", celebre=" + celebre + "]";
    }
}
