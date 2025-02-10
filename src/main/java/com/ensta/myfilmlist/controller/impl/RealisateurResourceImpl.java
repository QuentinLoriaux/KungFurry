package com.ensta.myfilmlist.controller.impl;

import com.ensta.myfilmlist.controller.RealisateurResource;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.form.RealisateurForm;
import com.ensta.myfilmlist.model.Page;
import com.ensta.myfilmlist.model.Realisateur;
import static com.ensta.myfilmlist.mapper.RealisateurMapper.convertRealisateurFormToRealisateur;
import com.ensta.myfilmlist.service.MyFilmsService;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/realisateur")
@Validated
public class RealisateurResourceImpl implements RealisateurResource {
    private final MyFilmsService myFilmsService;

    @Autowired
    public RealisateurResourceImpl(MyFilmsService myFilmsService) {
        this.myFilmsService = myFilmsService;
    }

//    @Override
//    @GetMapping
//    public ResponseEntity<List<RealisateurDTO>> getAllRealisateurs() throws ControllerException {
//        try {
//            List<RealisateurDTO> realisateurs = myFilmsService.findAllRealisateurs();
//            if (realisateurs.isEmpty()) {
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.ok(realisateurs);
//        } catch (Exception e) {
//            throw new ControllerException("Erreur lors de la récupération des réalisateurs", e);
//        }
//    }

    @Override
    @GetMapping
    public ResponseEntity<Page<RealisateurDTO>> getAllRealisateurs(@RequestParam int page,@RequestParam int size) throws ControllerException {
        try {
            Page<RealisateurDTO> realisateurs = myFilmsService.findAllRealisateurs(page, size);
            if (realisateurs.getData().isEmpty()) {
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
    public ResponseEntity<RealisateurDTO> createRealisateur(@RequestBody @Valid RealisateurForm realisateurForm, @RequestHeader String Authorization) throws ControllerException {
        try {
            RealisateurDTO realisateur = myFilmsService.createRealisateur(realisateurForm);
            return ResponseEntity.ok(realisateur);
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la création du réalisateur : " + e.getMessage(), e);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RealisateurDTO> updateRealisateur(@PathVariable long id, @RequestBody @Valid RealisateurForm realisateurForm, @RequestHeader String Authorization) throws ControllerException {
        try {
            Realisateur realisateur = convertRealisateurFormToRealisateur(realisateurForm);
            realisateur.setId(id);
            RealisateurDTO realisateurDTO = myFilmsService.updateRealisateur(realisateur);
            return ResponseEntity.ok(realisateurDTO);
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la mise à jour du réalisateur : " + e.getMessage(), e);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRealisateur(@PathVariable long id, @RequestHeader String Authorization) throws ControllerException {
        try {
            myFilmsService.deleteRealisateur(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la suppression du réalisateur : " + e.getMessage(), e);
        }
    }



}
