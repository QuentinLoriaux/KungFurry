package com.ensta.myfilmlist.dao;

import java.util.List;

import com.ensta.myfilmlist.model.Note;


public interface NoteDAO {


    /**
     * Renvoie la liste de toutes les notes.
     *
     * @return la liste de toutes les notes
     */
    public List<Note> findAll();

    /**
     * Ajoute une note.
     *
     * @param note la note à ajouter
     * @return la note ajoutée avec son id
     */
    public Note save(Note note);

    /**
     * Récupère une note par son id.
     * @param id l'id de la note à récupérer
     * @return la note récupérée
     */
    public Note findById(long id);

    /**
     * Supprime une note.
     * @param note la note à supprimer
     */
    public void delete(Note note);

    /**
     * Recherche les notes par un film.
     * @param filmId l'id du film
     * @return la liste des notes du film
     */
    public List<Note> findByFilmId(long filmId);

    /**
     * Met à jour une note.
     * @param note la note à mettre à jour
     * @return la note mise à jour
     */
    public Note update(Note note);

}