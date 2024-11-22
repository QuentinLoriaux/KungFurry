package com.ensta.myfilmlist.mapper;

import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.model.Realisateur;

public class RealisateurMapper {
    /**
     * Transforme un Realisateur en RealisateurDTO
     * @param realisateur
     * @return RealisateurDTO
     */
    public static RealisateurDTO convertRealisateurToRealisateurDTO(Realisateur realisateur){
        RealisateurDTO realisateurDTO = new RealisateurDTO();
        realisateurDTO.setId(realisateur.getId());
        realisateurDTO.setNom(realisateur.getNom());
        realisateurDTO.setPrenom(realisateur.getPrenom());
        realisateurDTO.setCelebre(realisateur.isCelebre());
        realisateurDTO.setDateNaissance(realisateur.getDateNaissance());
        return realisateurDTO;
    }

    /**
     * Transforme un RealisateurDTO en Realisateur
     * @param realisateurDTO
     * @return Realisateur
     */
    public static Realisateur convertRealisateurDTOToRealisateur(RealisateurDTO realisateurDTO) {
        Realisateur realisateur = new Realisateur();
        realisateur.setId(realisateurDTO.getId());
        realisateur.setNom(realisateurDTO.getNom());
        realisateur.setPrenom(realisateurDTO.getPrenom());
        realisateur.setCelebre(realisateurDTO.getCelebre());
        realisateur.setDateNaissance(realisateurDTO.getDateNaissance());
        return realisateur;
    }

}
