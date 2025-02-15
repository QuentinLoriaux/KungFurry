package com.ensta.myfilmlist.controller;

import javax.validation.Valid;

import com.ensta.myfilmlist.form.CommentaireForm;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ensta.myfilmlist.dto.CommentaireDTO;
import com.ensta.myfilmlist.exception.ControllerException;

import io.swagger.v3.oas.annotations.tags.Tag;

@Api(tags = "Commentaire")
@Tag(name = "Commentaire", description = "Opérations sur les commentaires")
public interface CommentaireRessource {

    /**
     * Crée un nouveau commentaire.
     *
     * @param filmId l'identifiant du film
     * @param content le texte du commentaire à créer
     * @return le commentaireDTO créé
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Créer un commentaire",
            notes = "Permet de créer un commentaire.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commentaire créé avec succès"),
            @ApiResponse(code = 400, message = "Erreur dans les données fournies"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommentaireDTO> createCommentaire(@Valid @RequestBody CommentaireForm commentaireForm, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Modifie un commentaire existant.
     *
     * @param id l'identifiant du commentaire à modifier
     * @param commentaireForm les données du commentaire à modifier
     * @return le commentaireDTO modifié
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Modifier un commentaire",
            notes = "Permet de modifier un commentaire.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commentaire modifié avec succès"),
            @ApiResponse(code = 400, message = "Erreur dans les données fournies"),
            @ApiResponse(code = 404, message = "Commentaire non trouvé"),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommentaireDTO> updateCommentaire(@PathVariable("id") long id, @Valid @RequestBody CommentaireForm commentaireForm, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Supprime un commentaire existant.
     *
     * @param id l'identifiant du commentaire à supprimer
     * @return ResponseEntity
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Supprimer un commentaire",
            notes = "Permet de supprimer un commentaire.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commentaire supprimé avec succès"),
            @ApiResponse(code = 404, message = "Commentaire non trouvé"),
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteCommentaire(@PathVariable("id") long id, @RequestHeader String Authorization) throws ControllerException;

}
