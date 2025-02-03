package com.ensta.myfilmlist.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ensta.myfilmlist.controller.CommentaireRessource;
import com.ensta.myfilmlist.dto.CommentaireDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.service.MyFilmsService;

@RestController
@RequestMapping("commentaires")
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
            CommentaireDTO commentaire = new CommentaireDTO();
            commentaire.setText(content);
            commentaire = myFilmsService.addCommentaire(commentaire, filmId);
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
            commentaire = myFilmsService.editCommentaire(commentaire);
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
