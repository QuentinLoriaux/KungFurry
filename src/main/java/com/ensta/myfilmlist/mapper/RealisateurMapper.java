package com.ensta.myfilmlist.mapper;

import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.form.RealisateurForm;
import com.ensta.myfilmlist.model.Realisateur;
import static com.ensta.myfilmlist.mapper.FilmMapper.convertFilmsDTOToFilms;

import java.util.ArrayList;
import java.util.List;

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
        realisateur.setCelebre(realisateurDTO.isCelebre());
        realisateur.setDateNaissance(realisateurDTO.getDateNaissance());
        if (realisateurDTO.getFilmRealises() != null){
            realisateur.setFilmRealises(convertFilmsDTOToFilms(realisateurDTO.getFilmRealises()));
        }
        return realisateur;
    }

    /**
     * Transforme une liste de Realisateur en une liste de RealisateurDTO
     * @param realisateurs
     * @return List<RealisateurDTO>
     */
    public static List<RealisateurDTO> convertRealisateurToRealisateurDTOs(List<Realisateur> realisateurs){
        return realisateurs.stream()
                .map(RealisateurMapper::convertRealisateurToRealisateurDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Transforme un RealisateurForm en Realisateur
     * @param realisateurForm
     * @return Realisateur
     */
    public static Realisateur convertRealisateurFormToRealisateur(RealisateurForm realisateurForm) {
        Realisateur realisateur = new Realisateur();
        realisateur.setNom(realisateurForm.getNom());
        realisateur.setPrenom(realisateurForm.getPrenom());
        realisateur.setDateNaissance(realisateurForm.getDateNaissance());
        realisateur.setCelebre(false);
        realisateur.setFilmRealises(new ArrayList<>());
        return realisateur;
    }

}
