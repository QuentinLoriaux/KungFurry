package com.ensta.myfilmlist.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensta.myfilmlist.controller.NoteRessource;
import com.ensta.myfilmlist.dto.NoteDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.service.MyFilmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<NoteDTO> updateNote(@RequestBody NoteDTO noteForm, @RequestParam long filmId) throws ControllerException {
        try {
            noteForm = myFilmsService.editNote(noteForm, filmId, "user");
            if (noteForm == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(noteForm);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteNoteById(@RequestBody NoteDTO noteForm, @RequestParam long filmId) throws ControllerException {
        try {
            myFilmsService.deleteNote(noteForm, filmId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
