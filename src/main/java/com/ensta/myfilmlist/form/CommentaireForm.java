package com.ensta.myfilmlist.form;

import javax.validation.constraints.Positive;

public class CommentaireForm {
    private String content;

    @Positive
    private long filmId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }
}
