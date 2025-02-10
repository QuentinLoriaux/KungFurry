package com.ensta.myfilmlist.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param Authorization le token de l'utilisateur
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
    ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs(@RequestHeader String Authorization) throws ControllerException;

    /**
     * Renvoie le utilisateur ayant l'identifiant spécifié.
     *
     * @param username l'identifiant du utilisateur à récupérer
     * @param Authorization le token de l'utilisateur
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
    ResponseEntity<UtilisateurDTO> getUtilisateurByUsername(@PathVariable String username, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Crée un utilisateur à partir des informations fournies.
     *
     * @param utilisateurForm les informations du utilisateur à créer
     * @param Authorization le token de l'utilisateur
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
    ResponseEntity<UtilisateurDTO> createUtilisateur(@RequestBody @Valid UtilisateurForm utilisateurForm, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Supprime un utilisateur à partir de son identifiant.
     *
     * @param id l'identifiant du utilisateur à supprimer
     * @param Authorization le token de l'utilisateur
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
    @DeleteMapping(value = "/{username}")
    ResponseEntity<?> deleteUtilisateur(@PathVariable String username, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Met à jour un utilisateur à partir de son identifiant et des informations fournies.
     *
     * @param id l'identifiant du utilisateur à mettre à jour
     * @param utilisateurForm les informations du utilisateur à mettre à jour
     * @param Authorization le token de l'utilisateur
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
    @PutMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable String username, @RequestBody @Valid UtilisateurForm utilisateurForm, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Connexion à partir de son identifiant et des informations fournies.
     *
     * @param utilisateurForm les informations du utilisateur à connecter
     * @return le token de connexion
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Se connecter",
            notes = "Permet de se connecter à partir de son identifiant et des informations fournies.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilisateur connecté avec succès"),
            @ApiResponse(code = 404, message = "Utilisateur non trouvé"),
            @ApiResponse(code = 400, message = "Requête incorrecte"),
    })
    @PutMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> login(@RequestBody @Valid UtilisateurForm utilisateurForm) throws ControllerException;
}
