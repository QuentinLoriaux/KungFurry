package com.ensta.myfilmlist.model;

public class Utilisateur {
    private long id;
    private String nom;
    private String prenom;

    public Utilisateur() {
        this.id = 0;
        this.nom = "";
        this.prenom = "";
    }

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
}
