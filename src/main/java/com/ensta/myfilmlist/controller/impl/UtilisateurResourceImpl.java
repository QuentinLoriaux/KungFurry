package com.ensta.myfilmlist.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ensta.myfilmlist.controller.UtilisateurResource;
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
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs(@RequestHeader String Authorization) throws ControllerException {
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
    public ResponseEntity<UtilisateurDTO> getUtilisateurByUsername(@PathVariable String username, @RequestHeader String Authorization) throws ControllerException {
        try {
            UtilisateurDTO user = myFilmsService.findUtilisateurByUsername(username);
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
    public ResponseEntity<UtilisateurDTO> createUtilisateur(@RequestBody @Valid UtilisateurForm userForm, @RequestHeader String Authorization) throws ControllerException {
        try {
            UtilisateurDTO user = myFilmsService.createUtilisateur(userForm);
            return ResponseEntity.ok(user);
        } catch (ServiceException e) {
            throw new ControllerException("Erreur lors de la création du user : " + e.getMessage(), e);
        }
    }

    @Override
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable String username, @RequestHeader String Authorization) throws ControllerException {
        try {
            myFilmsService.deleteUtilisateur(username);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new ControllerException("Erreur lors de la suppression du user", e);
        }
    }

    @Override
    @PutMapping("/{username}")
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable String username, @RequestBody @Valid UtilisateurForm userForm, @RequestHeader String Authorization) throws ControllerException {
        try {
            Utilisateur user = convertUtilisateurFormToUtilisateur(userForm);
            UtilisateurDTO userDTO = myFilmsService.updateUtilisateur(user);
            return ResponseEntity.ok(userDTO);
        } catch (ServiceException e) {
            throw new ControllerException("Erreur lors de la mise à jour du user : " + e.getMessage(), e);
        }
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UtilisateurForm userForm) throws ControllerException {
        try {
            UtilisateurDTO userDTO = myFilmsService.findUtilisateurByUsernamePassword(userForm.getUsername(), userForm.getPassword());
            if (userDTO != null) {
                return ResponseEntity.ok(myFilmsService.createToken(userDTO));
            }
            throw new ServiceException("User not found");
        } catch (ServiceException e) {
            throw new ControllerException("Erreur lors de la connexion : " + e.getMessage(), e);
        }
    }
}

