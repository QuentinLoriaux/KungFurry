package com.ensta.myfilmlist.controller.impl;

import java.util.List;

import javax.validation.Valid;

import com.ensta.myfilmlist.controller.UtilisateurResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensta.myfilmlist.dto.UtilisateurDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.form.UtilisateurForm;
import static com.ensta.myfilmlist.mapper.UtilisateurMapper.convertUtilisateurFormToUtilisateur;
import com.ensta.myfilmlist.model.Utilisateur;
import com.ensta.myfilmlist.service.MyFilmsService;

@RestController
@RequestMapping("/user")
@Validated
public class UtilisateurResourceImpl implements UtilisateurResource {
    private final MyFilmsService myFilmsService;

    @Autowired
    public UtilisateurResourceImpl(MyFilmsService myFilmsService) {
        this.myFilmsService = myFilmsService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs() throws ControllerException {
        try {
            List<UtilisateurDTO> users = myFilmsService.findAllUtilisateurs();
            if (users.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la récupération des users", e);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable long id) throws ControllerException {
        try {
            UtilisateurDTO user = myFilmsService.findUtilisateurById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la récupération du user : " + e.getMessage(), e);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<UtilisateurDTO> createUtilisateur(@RequestBody @Valid UtilisateurForm userForm) throws ControllerException {
        try {
            UtilisateurDTO user = myFilmsService.createUtilisateur(userForm);
            return ResponseEntity.ok(user);
        } catch (ServiceException e) {
            throw new ControllerException("Erreur lors de la création du user : " + e.getMessage(), e);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable long id) throws ControllerException {
        try {
            myFilmsService.deleteUtilisateur(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la suppression du user", e);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable long id, @RequestBody @Valid UtilisateurForm userForm) throws ControllerException {
        try {
            Utilisateur user = convertUtilisateurFormToUtilisateur(userForm);
            user.setId(id);
            UtilisateurDTO userDTO = myFilmsService.updateUtilisateur(user);
            return ResponseEntity.ok(userDTO);
        } catch (ServiceException e) {
            throw new ControllerException("Erreur lors de la mise à jour du user : " + e.getMessage(), e);
        }
    }
}

