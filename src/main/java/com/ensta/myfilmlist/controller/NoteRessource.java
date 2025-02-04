package com.ensta.myfilmlist.controller;

import javax.validation.Valid;

import com.ensta.myfilmlist.form.NoteForm;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ensta.myfilmlist.dto.NoteDTO;
import com.ensta.myfilmlist.exception.ControllerException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Api(tags = "Note")
@Tag(name = "Note", description = "Opérations sur les notes")
public interface NoteRessource {

    
    /**
     * Ajoute un note à la liste des notes.
     *
     * @param noteForm le formulaire de note à ajouter
     * @param filmId l'identifiant du film associé
     * @return le NoteDTO créé
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Ajouter un note",
            notes = "Permet d'ajouter un note à la liste des notes.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Note ajoutée avec succès"),
            @ApiResponse(code = 400, message = "Note non ajoutée"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<NoteDTO> addNote(@Valid @RequestBody NoteForm noteForm, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Modifie un note existant.
     *
     * @param noteForm le formulaire de note à modifier
     * @param filmId l'identifiant du film associé
     * @return le NoteDTO modifié
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Modifier un note",
            notes = "Permet de modifier un note existant.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Note modifiée avec succès"),
            @ApiResponse(code = 400, message = "Note non modifiée"),
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<NoteDTO> updateNote(@PathVariable long id, @Valid @RequestBody NoteForm noteForm, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Supprime un note existant.
     *
     * @param noteForm le formulaire de note à supprimer
     * @param filmId l'identifiant du film associé
     * @return ResponseEntity
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Supprimer un note",
            notes = "Permet de supprimer un note existant.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Note supprimée avec succès"),
            @ApiResponse(code = 404, message = "Note non trouvée"),
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteNoteById(@PathVariable long id, @RequestParam long filmId, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Récupère une note en fonction du filmId et de l'username.
     * @param filmId l'identifiant du film
     * @param username l'identifiant de l'utilisateur
     * @return le NoteDTO récupéré
     * @throws ControllerException en cas d'erreur de traitement
     */
        @ApiOperation(
                value = "Récupérer une note",
                notes = "Permet de récupérer une note en fonction du filmId et de l'username.",
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Note récupérée avec succès"),
                @ApiResponse(code = 404, message = "Note non trouvée"),
        })

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        ResponseEntity<NoteDTO> getNote(@RequestParam long filmId) throws ControllerException;

}
