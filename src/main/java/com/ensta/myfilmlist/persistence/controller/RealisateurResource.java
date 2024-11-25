package com.ensta.myfilmlist.persistence.controller;

import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.form.RealisateurForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

// L’API s’appelle « Film » et utilise le Tag « Film »
// Le tag « Film » contient la description de l’API
@Api(tags = "Réalisateur")
@Tag(name = "Réalisateur", description = "Opérations sur les réalisateurs")
public interface RealisateurResource {

    /**
     * Renvoie la liste non nulle de tous les réalisateurs disponibles.
     *
     * @return List of RealisateurDTO
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Lister les réalisateurs",
            notes = "Permet de renvoyer la liste de tous les réalisateurs.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des réalisateurs trouvée avec succès"),
            @ApiResponse(code = 404, message = "Liste des réalisateurs vide"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RealisateurDTO>> getAllRealisateurs() throws ControllerException;

    /**
     * Renvoie le réalisateur ayant l'id donné.
     *
     * @return le RealisateurDTO associé ou une erreur 404 si non trouvé
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Récupérer un réalisateur",
            notes = "Permet de renvoyer un réalisateur en fonction de son identifiant.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Réalisateur trouvé avec succès"),
            @ApiResponse(code = 404, message = "Réalisateur non trouvé"),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RealisateurDTO> getRealisateurById(@PathVariable long id) throws ControllerException;

    /**
     * Crée un réalisateur.
     *
     * @param realisateurForm le formulaire de création du réalisateur
     * @return le RealisateurDTO créé
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Créer un réalisateur",
            notes = "Permet de créer un réalisateur.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Réalisateur créé avec succès"),
            @ApiResponse(code = 404, message = "Erreur lors de la création du réalisateur"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createRealisateur(@RequestBody @Valid RealisateurForm realisateurForm) throws ControllerException;
}
