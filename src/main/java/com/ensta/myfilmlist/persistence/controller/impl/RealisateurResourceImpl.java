package com.ensta.myfilmlist.persistence.controller.impl;

import com.ensta.myfilmlist.form.RealisateurForm;
import com.ensta.myfilmlist.service.MyFilmsService;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/realisateur")
@Validated
public class RealisateurResourceImpl implements com.ensta.myfilmlist.persistence.controller.RealisateurResource {
    private final MyFilmsService myFilmsService;

    @Autowired
    public RealisateurResourceImpl(MyFilmsService myFilmsService) {
        this.myFilmsService = myFilmsService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RealisateurDTO>> getAllRealisateurs() throws ControllerException {
        try {
            List<RealisateurDTO> realisateurs = myFilmsService.findAllRealisateurs();
            if (realisateurs.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(realisateurs);
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la récupération des réalisateurs", e);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RealisateurDTO> getRealisateurById(long id) throws ControllerException {
        try {
            RealisateurDTO realisateur = myFilmsService.findRealisateurById(id);
            if (realisateur == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(realisateur);
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la récupération du réalisateur : " + e.getMessage(), e);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<RealisateurDTO> createRealisateur(@RequestBody @Valid RealisateurForm realisateurForm) throws ControllerException {
        try {
            RealisateurDTO realisateur = myFilmsService.createRealisateur(realisateurForm);
            return ResponseEntity.ok(realisateur);
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la création du réalisateur : " + e.getMessage(), e);
        }
    }



}
