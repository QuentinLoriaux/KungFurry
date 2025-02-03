package com.ensta.myfilmlist.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ensta.myfilmlist.controller.NoteRessource;
import com.ensta.myfilmlist.dto.NoteDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.service.MyFilmsService;
import com.ensta.myfilmlist.model.Note;

@RestController
@RequestMapping("notes")
public class NoteRessourceImpl implements NoteRessource {
    private final MyFilmsService myFilmsService;

    @Autowired
    public NoteRessourceImpl(MyFilmsService myFilmsService) {
        this.myFilmsService = myFilmsService;
    }

    @Override
    public ResponseEntity<NoteDTO> addNote(@RequestBody int noteForm, @RequestParam long filmId) throws ControllerException {
        try {
            NoteDTO note = new NoteDTO();
            note.setValue(noteForm);
            note = myFilmsService.addNote(note, filmId ,"user");
            if (note == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(note);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
        
    }

    @Override
    public ResponseEntity<NoteDTO> updateNote(@PathVariable long id, @RequestBody int noteForm, @RequestParam long filmId) throws ControllerException {
        try {
            Note note = new Note();
            note.setNote(noteForm);
            note.setId(id);
            NoteDTO noteDTO = myFilmsService.editNote(note, filmId ,"user");

            if (note == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(noteDTO);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteNoteById(long id, @RequestParam long filmId) throws ControllerException {
        try {
            myFilmsService.deleteNote(id, filmId, "user");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<NoteDTO> getNote(@RequestParam long id) throws ControllerException {
        try {
            NoteDTO note = myFilmsService.getNote(id, "user");
            if (note == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(note);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
