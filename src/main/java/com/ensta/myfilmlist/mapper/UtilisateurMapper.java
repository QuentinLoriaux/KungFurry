package com.ensta.myfilmlist.mapper;

import java.util.List;

import com.ensta.myfilmlist.dto.UtilisateurDTO;
import com.ensta.myfilmlist.form.UtilisateurForm;
import com.ensta.myfilmlist.model.Utilisateur;

public class UtilisateurMapper {
    /**
     * Transforme un Utilisateur en UtilisateurDTO
     * @param utilisateur
     * @return UtilisateurDTO
     */
    public static UtilisateurDTO convertUtilisateurToUtilisateurDTO(Utilisateur utilisateur){
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(utilisateur.getId());
        utilisateurDTO.setUsername(utilisateur.getUsername());
        utilisateurDTO.setMd5Hex(utilisateur.getMd5Hex());
        utilisateurDTO.setId(utilisateur.getId());
        utilisateurDTO.setRoleId(utilisateur.getRoleId());
        return utilisateurDTO;
    }

    /**
     * Transforme un UtilisateurDTO en Utilisateur
     * @param utilisateurDTO
     * @return Utilisateur
     */
    public static Utilisateur convertUtilisateurDTOToUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDTO.getId());
        utilisateur.setUsername(utilisateurDTO.getUsername());
        utilisateur.setMd5Hex(utilisateurDTO.getMd5Hex());
        utilisateur.setRoleId(utilisateurDTO.getRoleId());
        return utilisateur;
    }

    /**
     * Transforme une liste de Utilisateur en une liste de UtilisateurDTO
     * @param utilisateurs
     * @return List<UtilisateurDTO>
     */
    public static List<UtilisateurDTO> convertUtilisateurToUtilisateurDTOs(List<Utilisateur> utilisateurs){
        return utilisateurs.stream()
                .map(UtilisateurMapper::convertUtilisateurToUtilisateurDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Transforme un UtilisateurForm en Utilisateur
     * @param utilisateurForm
     * @return Utilisateur
     */
    public static Utilisateur convertUtilisateurFormToUtilisateur(UtilisateurForm utilisateurForm) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(utilisateurForm.getUsername());
        utilisateur.setPassword(utilisateurForm.getPassword());
        utilisateur.setRoleId(utilisateurForm.getRoleId());
        return utilisateur;
    }

}
