package com.ensta.myfilmlist.persistence.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ensta.myfilmlist.dto.UtilisateurDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.form.UtilisateurForm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

// L’API s’appelle « Utilisateur » et utilise le Tag « Utilisateur »
// Le tag « Utilisateur » contient la description de l’API
@Api(tags = "Utilisateur")
@Tag(name = "Utilisateur", description = "Opérations sur les utilisateurs")
public interface UtilisateurResource {

    /**
     * Renvoie la liste non nulle de tous les utilisateurs disponibles, ainsi que leur réalisateur associé.
     *
     * @return List of UtilisateurDTO
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Lister les utilisateurs",
            notes = "Permet de renvoyer la liste de tous les utilisateurs.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des utilisateurs trouvée avec succès"),
            @ApiResponse(code = 404, message = "Liste des utilisateurs vide"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs() throws ControllerException;

    /**
     * Renvoie le utilisateur ayant l'id donné.
     *
     * @param id l'identifiant du utilisateur
     * @return le UtilisateurDTO associé ou une erreur 404 si non trouvé
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Récupérer un utilisateur",
            notes = "Permet de renvoyer un utilisateur en fonction de son identifiant.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilisateur trouvé avec succès"),
            @ApiResponse(code = 404, message = "Utilisateur non trouvé"),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable long id) throws ControllerException;

    /**
     * Crée un utilisateur à partir des informations fournies.
     *
     * @param utilisateurForm les informations du utilisateur à créer
     * @return le UtilisateurDTO créé
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Créer un utilisateur",
            notes = "Permet de créer un utilisateur à partir des informations fournies.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Utilisateur créé avec succès"),
            @ApiResponse(code = 400, message = "Requête incorrecte"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDTO> createUtilisateur(@RequestBody @Valid UtilisateurForm utilisateurForm) throws ControllerException;

    /**
     * Supprime un utilisateur à partir de son identifiant.
     *
     * @param id l'identifiant du utilisateur à supprimer
     * @return ResponseEntity
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Supprimer un utilisateur",
            notes = "Permet de supprimer un utilisateur à partir de son identifiant."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Utilisateur supprimé avec succès"),
            @ApiResponse(code = 404, message = "Utilisateur non trouvé"),
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteUtilisateur(@PathVariable long id) throws ControllerException;

    /**
     * Met à jour un utilisateur à partir de son identifiant et des informations fournies.
     *
     * @param id l'identifiant du utilisateur à mettre à jour
     * @param utilisateurForm les informations du utilisateur à mettre à jour
     * @return le UtilisateurDTO mis à jour
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Mettre à jour un utilisateur",
            notes = "Permet de mettre à jour un utilisateur à partir de son identifiant et des informations fournies.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilisateur mis à jour avec succès"),
            @ApiResponse(code = 404, message = "Utilisateur non trouvé"),
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable long id, @RequestBody @Valid UtilisateurForm utilisateurForm) throws ControllerException;
}