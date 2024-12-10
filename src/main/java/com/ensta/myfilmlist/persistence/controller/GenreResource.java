package com.ensta.myfilmlist.persistence.controller;

import com.ensta.myfilmlist.dto.GenreDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(tags = "Genre")
@Tag(name = "Genre", description = "Opérations sur les genres")
public interface GenreResource {

    /**
     * Renvoie la liste non nulle de tous les genres disponibles.
     *
     * @return List of GenreDTO
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Lister les genres",
            notes = "Permet de renvoyer la liste de tous les genres.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des genres trouvée avec succès"),
            @ApiResponse(code = 404, message = "Liste des genres vide"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<GenreDTO>> getAllGenres() throws ControllerException;

    /**
     * Renvoie le genre ayant l'id donné.
     *
     * @param id l'identifiant du genre
     * @return le GenreDTO associé ou une erreur 404 si non trouvé
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Récupérer un genre",
            notes = "Permet de renvoyer un genre en fonction de son identifiant.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Genre trouvé avec succès"),
            @ApiResponse(code = 404, message = "Genre non trouvé"),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GenreDTO> getGenre(@PathVariable("id") Long id) throws ControllerException;

}
