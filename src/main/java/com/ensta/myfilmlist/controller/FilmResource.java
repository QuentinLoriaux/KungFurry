package com.ensta.myfilmlist.controller;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.FilmDetailsDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.form.FilmForm;
import com.ensta.myfilmlist.model.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// L’API s’appelle « Film » et utilise le Tag « Film »
// Le tag « Film » contient la description de l’API
@Api(tags = "Film")
@Tag(name = "Film", description = "Opérations sur les films")
public interface FilmResource {

//    /**
//     * Renvoie la liste non nulle de tous les films disponibles, ainsi que leur réalisateur associé.
//     *
//     * @return List of FilmDTO
//     * @throws ControllerException en cas d'erreur de traitement
//     */
//    @ApiOperation(
//            value = "Lister les films",
//            notes = "Permet de renvoyer la liste de tous les films.",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Liste des films trouvée avec succès"),
//            @ApiResponse(code = 404, message = "Liste des films vide"),
//    })
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//
//    ResponseEntity<List<FilmDTO>> getAllFilms() throws ControllerException;

    /**
     * Renvoie la liste de films de la page donnée.
     *
     * @param page le numéro de la page
     * @param size la taille de la page
     * @param query la recherche
     * @param sort le tri
     * @param order l'ordre
     * @return âge of FilmDTO
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Lister les films",
            notes = "Permet de renvoyer la liste de films de la page donnée.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des films trouvée avec succès"),
            @ApiResponse(code = 404, message = "Liste des films vide"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<FilmDTO>> getAllFilms(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order)
            throws ControllerException;

    /**
     * Renvoie le film ayant l'id donné.
     *
     * @param id l'identifiant du film
     * @return le FilmDTO associé ou une erreur 404 si non trouvé
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Récupérer un film",
            notes = "Permet de renvoyer un film en fonction de son identifiant.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Film trouvé avec succès"),
            @ApiResponse(code = 404, message = "Film non trouvé"),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FilmDetailsDTO> getFilmById(@PathVariable long id) throws ControllerException;

    /**
     * Crée un film à partir des informations fournies.
     *
     * @param filmForm les informations du film à créer
     * @param Authorization le token d'authentification
     * @return le FilmDTO créé
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Créer un film",
            notes = "Permet de créer un film à partir des informations fournies.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Film créé avec succès"),
            @ApiResponse(code = 400, message = "Requête incorrecte"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FilmDTO> createFilm(@RequestBody @Valid FilmForm filmForm, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Supprime un film à partir de son identifiant.
     *
     * @param id l'identifiant du film à supprimer
     * @param Authorization le token d'authentification
     * @return ResponseEntity
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Supprimer un film",
            notes = "Permet de supprimer un film à partir de son identifiant."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Film supprimé avec succès"),
            @ApiResponse(code = 404, message = "Film non trouvé"),
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteFilm(@PathVariable long id, @RequestHeader String Authorization) throws ControllerException;

    /**
     * Met à jour un film à partir de son identifiant et des informations fournies.
     *
     * @param id l'identifiant du film à mettre à jour
     * @param filmForm les informations du film à mettre à jour
     * @param Authorization le token d'authentification
     * @return le FilmDTO mis à jour
     * @throws ControllerException en cas d'erreur de traitement
     */
    @ApiOperation(
            value = "Mettre à jour un film",
            notes = "Permet de mettre à jour un film à partir de son identifiant et des informations fournies.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Film mis à jour avec succès"),
            @ApiResponse(code = 404, message = "Film non trouvé"),
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FilmDTO> updateFilm(@PathVariable long id, @RequestBody @Valid FilmForm filmForm, @RequestHeader String Authorization) throws ControllerException;
}
