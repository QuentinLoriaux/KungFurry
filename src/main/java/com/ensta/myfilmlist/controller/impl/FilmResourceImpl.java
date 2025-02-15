package com.ensta.myfilmlist.controller.impl;

import com.ensta.myfilmlist.controller.FilmResource;
import com.ensta.myfilmlist.dto.FilmDetailsDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.form.FilmForm;
import com.ensta.myfilmlist.model.Page;
import com.ensta.myfilmlist.service.MyFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ensta.myfilmlist.mapper.FilmMapper.convertFilmFormToFilm;
import static com.ensta.myfilmlist.mapper.RealisateurMapper.convertRealisateurDTOToRealisateur;
import static com.ensta.myfilmlist.mapper.GenreMapper.convertGenreDTOToGenre;

@RestController
@RequestMapping("/film")
@Validated
public class FilmResourceImpl implements FilmResource {
    private final MyFilmsService myFilmsService;

    @Autowired
    public FilmResourceImpl(MyFilmsService myFilmsService) {
        this.myFilmsService = myFilmsService;
    }

//    @Override
//    @GetMapping
//    public ResponseEntity<List<FilmDTO>> getAllFilms() throws ControllerException {
//        try {
//            List<FilmDTO> films = myFilmsService.findAllFilms();
//            if (films.isEmpty()) {
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.ok(films);
//        } catch (Exception e) {
//            throw new ControllerException("Erreur lors de la récupération des films", e);
//        }
//    }

    @Override
    @GetMapping
    public ResponseEntity<Page<FilmDTO>> getAllFilms(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order)
            throws ControllerException {
        try {
            Page<FilmDTO> films = myFilmsService.findAllFilms(page, size, query, sort, order);
            if (films.getData().isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(films);
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la récupération des films"+ e.getMessage(), e);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FilmDetailsDTO> getFilmById(@PathVariable long id) throws ControllerException {
        try {
            FilmDetailsDTO film = myFilmsService.findFilmById(id);
            if (film == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(film);
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la récupération du film : " + e.getMessage(), e);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@RequestBody @Valid FilmForm filmForm, @RequestHeader String Authorization) throws ControllerException {
        try {
            FilmDTO film = myFilmsService.createFilm(filmForm);
            return ResponseEntity.ok(film);
        } catch (ServiceException e) {
            throw new ControllerException("Erreur lors de la création du film : " + e.getMessage(), e);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable long id, @RequestHeader String Authorization) throws ControllerException {
        try {
            myFilmsService.deleteFilm(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la suppression du film", e);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable long id, @RequestBody @Valid FilmForm filmForm, @RequestHeader String Authorization) throws ControllerException {
        try {
            Film film = convertFilmFormToFilm(filmForm);
            film.setId(id);
            film.setRealisateur(convertRealisateurDTOToRealisateur(myFilmsService.findRealisateurById(filmForm.getRealisateurId())));
            film.setGenre(convertGenreDTOToGenre(myFilmsService.findGenreById(filmForm.getGenreId())));
            FilmDTO filmDTO = myFilmsService.updateFilm(film);
            return ResponseEntity.ok(filmDTO);
        } catch (ServiceException e) {
            throw new ControllerException("Erreur lors de la mise à jour du film : " + e.getMessage(), e);
        }
    }
}

