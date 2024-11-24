package com.ensta.myfilmlist.persistence.controller;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface FilmResource {
    /**
     * Renvoie la liste non nulle de tous les films disponibles, ainsi que leur realisateur associé
     * @return List of FilmDTO
     * @throws ControllerException
     */
    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() throws ControllerException;
}
