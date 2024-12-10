package com.ensta.myfilmlist.persistence.controller.impl;

import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.service.MyFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreResourceImpl implements com.ensta.myfilmlist.persistence.controller.GenreResource {

    private final MyFilmsService myFilmsService;

    @Autowired
    public GenreResourceImpl(MyFilmsService myFilmsService) {
        this.myFilmsService = myFilmsService;
    }


    @Override
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() throws ControllerException {
        try {
            List<GenreDTO> genres = myFilmsService.findAllGenres();
            if (genres.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(genres);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenre(@PathVariable("id") Long id) throws ControllerException {
        try {
            GenreDTO genre = myFilmsService.findGenreById(id);
            if (genre == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(genre);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
