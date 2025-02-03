package com.ensta.myfilmlist.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CommentaireDTO> createCommentaire(@RequestParam long filmId, @RequestBody String commentaireForm) throws ControllerException {
        try {
            CommentaireDTO commentaire = new CommentaireDTO();
            commentaire.setText(commentaireForm);
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
    public ResponseEntity<CommentaireDTO> updateCommentaire(@RequestParam long filmId, @RequestBody String commentaireForm) throws ControllerException {
        try {
            CommentaireDTO commentaire = new CommentaireDTO();
            commentaire.setText(commentaireForm);
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
    public ResponseEntity<?> deleteCommentaire(@RequestParam long id) {
        try {
            myFilmsService.deleteCommentaire(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
