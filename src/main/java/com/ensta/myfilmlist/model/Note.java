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
 * Represente une note d'un film donnée par un utilisateur.
 */

 @Entity
    @Table(name = "note")
    public class Note {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
    
        @Column
        private int note;
    
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
    
        public int getNote() {
            return note;
        }

        public void setNote(int note) {
            this.note = note;
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