package com.ensta.myfilmlist.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ensta.myfilmlist.dto.CommentaireDTO;
import com.ensta.myfilmlist.form.CommentaireForm;
import com.ensta.myfilmlist.model.Commentaire;

/**
 * Effectue les conversions des Commentaires entre les couches de l'application.
 */

public class CommentaireMapper {

    /**
     * Convertit un commentaire en DTO.
     * 
     * @param commentaire le commentaire a convertir
     * @return Un DTO construit a partir des donnees du commentaire.
     */
    public static CommentaireDTO convertCommentaireToCommentaireDTO(Commentaire commentaire) {
        CommentaireDTO commentaireDTO = new CommentaireDTO();
        commentaireDTO.setId(commentaire.getId());
        commentaireDTO.setText(commentaire.getText());
        commentaireDTO.setDate(commentaire.getDate());
        commentaireDTO.setUsername(commentaire.getUtilisateur().getUsername());
        return commentaireDTO;
    }

    /**
     * Convertit un DTO en commentaire.
     * 
     * @param commentaireDTO le DTO a convertir
     * @return Un Commentaire construit a partir des donnes du DTO.
     */
    public static Commentaire convertCommentaireDTOToCommentaire(CommentaireDTO commentaireDTO) {
        Commentaire commentaire = new Commentaire();
        commentaire.setId(commentaireDTO.getId());
        commentaire.setText(commentaireDTO.getText());
        commentaire.setDate(commentaireDTO.getDate());
        return commentaire;
    }

    public static List<Commentaire> convertCommentaireDTOToCommentaires(List<CommentaireDTO> commentairesDTO) {
        return commentairesDTO.stream()
                .map(CommentaireMapper::convertCommentaireDTOToCommentaire)
                .collect(Collectors.toList());
    }

    public static List<CommentaireDTO> convertCommentairesToCommentaireDTOs(List<Commentaire> commentaires) {
        return commentaires.stream()
                .map(CommentaireMapper::convertCommentaireToCommentaireDTO)
                .collect(Collectors.toList());
    }

    public static Commentaire convertCommentaireFormToCommentaire(CommentaireForm commentaireForm) {
        Commentaire commentaire = new Commentaire();
        commentaire.setText(commentaireForm.getContent());
        return commentaire;
    }
}
