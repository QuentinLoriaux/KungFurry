package com.ensta.myfilmlist.persistence.controller.impl;

import com.ensta.myfilmlist.MyfilmlistTests;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.service.MyFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmResourceImpl implements com.ensta.myfilmlist.persistence.controller.FilmResource {
    private final MyFilmsService myFilmsService;

    @Autowired
    public FilmResourceImpl(MyFilmsService myFilmsService) {
        this.myFilmsService = myFilmsService;
    }

    @Override
    public ResponseEntity<List<FilmDTO>> getAllFilms() throws ControllerException {
        try {
            return ResponseEntity.ok(myFilmsService.findAllFilms());
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la récupération des films", e);
        }
    }
}
