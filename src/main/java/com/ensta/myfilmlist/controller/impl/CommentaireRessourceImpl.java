package com.ensta.myfilmlist.controller.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ensta.myfilmlist.controller.CommentaireRessource;
import com.ensta.myfilmlist.dto.CommentaireDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.service.MyFilmsService;

@RestController
@RequestMapping("commentaire")
public class CommentaireRessourceImpl implements CommentaireRessource {

    private final MyFilmsService myFilmsService;

    @Autowired
    public CommentaireRessourceImpl(MyFilmsService myFilmsService) {
        this.myFilmsService = myFilmsService;
    }

    @Override
    @PostMapping
    public ResponseEntity<CommentaireDTO> createCommentaire(@RequestParam long filmId, @RequestBody String content) throws ControllerException {
        try {
            CommentaireDTO commentaire = myFilmsService.addCommentaire(content, filmId, "user");
            if (commentaire == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(commentaire);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CommentaireDTO> updateCommentaire(@PathVariable long id, @RequestBody String content, @RequestParam long filmId) throws ControllerException {
        try {
            CommentaireDTO commentaire = new CommentaireDTO();
            commentaire.setText(content);
            commentaire.setId(id);
            commentaire.setDate(LocalDate.now());
            commentaire = myFilmsService.editCommentaire(commentaire, filmId, "user");
            if (commentaire == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(commentaire);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommentaire(@PathVariable long id) {
        try {
            myFilmsService.deleteCommentaire(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
