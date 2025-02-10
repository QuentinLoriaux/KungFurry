package com.ensta.myfilmlist.model;

import javax.persistence.*;

@Entity
@Table(name = "vue")
public class Vue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
