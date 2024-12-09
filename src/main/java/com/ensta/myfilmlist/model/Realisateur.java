package com.ensta.myfilmlist.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "realisateur")
public class Realisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "realisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Film> filmRealises = new ArrayList<>();

    @Column
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

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setFilmRealises(List<Film> filmRealises) {
        this.filmRealises = filmRealises;
    }
    public List<Film> getFilmRealises() {
        return filmRealises;
    }

    public void setCelebre(Boolean celebre) {
        this.celebre = celebre;
    }
    public Boolean isCelebre() {
        return celebre;
    }
}
