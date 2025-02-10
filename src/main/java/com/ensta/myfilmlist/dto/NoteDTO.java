package com.ensta.myfilmlist.dto;

import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Utilisateur;

/**
 * Contient les donnees d'une Note.
 */

public class NoteDTO {
    private long id;
    private int value;

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

    @Override
    public String toString() {
        return "NoteDTO [id=" + id + ", value=" + value + "]";
    }
}