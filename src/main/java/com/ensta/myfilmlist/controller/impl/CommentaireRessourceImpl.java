package com.ensta.myfilmlist.controller.impl;

import java.time.LocalDate;

import com.ensta.myfilmlist.form.CommentaireForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ensta.myfilmlist.controller.CommentaireRessource;
import com.ensta.myfilmlist.dto.CommentaireDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.service.MyFilmsService;

import javax.validation.Valid;

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
    public ResponseEntity<CommentaireDTO> createCommentaire(@Valid @RequestBody CommentaireForm commentaireForm, @RequestHeader String Authorization) throws ControllerException {
        try {
            String username = Authorization.split(" ")[1].split(";")[0];
            CommentaireDTO commentaire = myFilmsService.addCommentaire(commentaireForm, username);
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
    public ResponseEntity<CommentaireDTO> updateCommentaire(@PathVariable("id") long id, @Valid @RequestBody CommentaireForm commentaireForm, @RequestHeader String Authorization) throws ControllerException {
        try {
            String username = Authorization.split(" ")[1].split(";")[0];
            CommentaireDTO commentaire = myFilmsService.editCommentaire(commentaireForm, username, id);
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
    public ResponseEntity<?> deleteCommentaire(@PathVariable("id") long id, @RequestHeader String Authorization) {
        try {
            myFilmsService.deleteCommentaire(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
