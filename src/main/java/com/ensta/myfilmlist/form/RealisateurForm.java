package com.ensta.myfilmlist.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class RealisateurForm {

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Past
    private LocalDate dateNaissance;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setDateNaissance(LocalDate dateNaissance){
        this.dateNaissance = dateNaissance;
    }

    public LocalDate getDateNaissance(){
        return dateNaissance;
    }
}
