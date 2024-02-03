package com.app.motivation_services.controller;

import com.app.motivation_services.model.Note;
import com.app.motivation_services.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/note")
    public ResponseEntity<Note> createAuthor(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.saveNote(note), HttpStatus.CREATED);
    }
}
