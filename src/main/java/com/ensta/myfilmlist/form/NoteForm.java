package com.ensta.myfilmlist.form;

import javax.validation.constraints.Positive;

public class NoteForm {

    @Positive
    private long filmId;

    @Positive
    private int note;

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
