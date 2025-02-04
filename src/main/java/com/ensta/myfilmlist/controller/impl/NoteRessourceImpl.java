package com.ensta.myfilmlist.controller.impl;

import com.ensta.myfilmlist.form.NoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ensta.myfilmlist.controller.NoteRessource;
import com.ensta.myfilmlist.dto.NoteDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.service.MyFilmsService;
import com.ensta.myfilmlist.model.Note;

import javax.validation.Valid;

@RestController
@RequestMapping("notes")
public class NoteRessourceImpl implements NoteRessource {
    private final MyFilmsService myFilmsService;

    @Autowired
    public NoteRessourceImpl(MyFilmsService myFilmsService) {
        this.myFilmsService = myFilmsService;
    }

    @Override
    public ResponseEntity<NoteDTO> addNote(@Valid @RequestBody NoteForm noteForm, @RequestHeader String Authorization) throws ControllerException {
        try {
            NoteDTO note = myFilmsService.addNote(noteForm ,"user");
            if (note == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(note);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
        
    }

    @Override
    public ResponseEntity<NoteDTO> updateNote(@PathVariable long id, @Valid @RequestBody NoteForm noteForm, @RequestHeader String Authorization) throws ControllerException {
        try {
            NoteDTO note = myFilmsService.editNote(noteForm ,"user", id);
            if (note == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(note);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteNoteById(long id, @RequestParam long filmId, @RequestHeader String Authorization) throws ControllerException {
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
