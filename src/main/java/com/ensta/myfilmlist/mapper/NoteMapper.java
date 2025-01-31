package com.ensta.myfilmlist.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ensta.myfilmlist.dto.NoteDTO;
import com.ensta.myfilmlist.model.Note;

/**
 * Effectue les conversions des Notes entre les couches de l'application.
 */

public class NoteMapper {
    
    /**
     * Convertit une liste de notes en liste de DTO.
     * 
     * @param notes la liste des notes
     * @return Une liste non nulle de dtos construite a partir de la liste des notes.
     */
    public static List<NoteDTO> convertNoteToNoteDTOs(List<Note> notes) {
        return notes.stream()
                .map(NoteMapper::convertNoteToNoteDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit une note en DTO.
     * 
     * @param note la note a convertir
     * @return Un DTO construit a partir des donnees de la note.
     */
    public static NoteDTO convertNoteToNoteDTO(Note note) {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(note.getId());
        noteDTO.setValue(note.getNote());
        return noteDTO;
    }

    /**
     * Convertit un DTO en note.
     * 
     * @param noteDTO le DTO a convertir
     * @return Un Note construit a partir des donnes du DTO.
     */
    public static Note convertNoteDTOToNote(NoteDTO noteDTO) {
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setNote(noteDTO.getValue());
        return note;
    }
} 