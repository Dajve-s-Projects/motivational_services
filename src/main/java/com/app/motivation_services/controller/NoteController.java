package com.app.motivation_services.controller;

import com.app.motivation_services.model.Emotion;
import com.app.motivation_services.model.Note;
import com.app.motivation_services.service.EmotionService;
import com.app.motivation_services.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class NoteController {

    private final NoteService noteService;
    private final EmotionService emotionService;

    @Autowired
    public NoteController(NoteService noteService, EmotionService emotionService) {
        this.noteService = noteService;
        this.emotionService = emotionService;
    }

    @GetMapping("/note")
    public ResponseEntity<List<Note>> getAllNotes() {
        try {
            List<Note> notesList = new ArrayList<>(noteService.getAllBooks());

            if (notesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(notesList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{note-id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("note-id") Long id) {
        Optional<Note> note = noteService.getBookById(id);

        return note.map(
                        singleNote -> new ResponseEntity<>(singleNote, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                );
    }

    @PutMapping("/{note-id}")
    public ResponseEntity<Note> updateNoteById(@PathVariable("note-id") Long id, @RequestBody Note newNoteData) {
        Optional<Note> oldNoteData = noteService.getBookById(id);

        if (oldNoteData.isPresent()) {
            Note updatedNoteData = oldNoteData.get();
            updatedNoteData.setActualNote(newNoteData.getActualNote());
            updatedNoteData.setDateOfNoteSubmission(newNoteData.getDateOfNoteSubmission());

            Note noteObj = noteService.saveNote(updatedNoteData);
            return new ResponseEntity<>(noteObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // needs to be worked on
    @PostMapping("/note")
    public ResponseEntity<String> createNote(@RequestBody Note note) {
        Optional<Emotion> emotion = emotionService.getEmotionByEmotion(note.getEmotion());

        if (emotion.isEmpty()) {
            return new ResponseEntity<>(String.format("There is no emotion with the name of %s", note.getEmotion()),
                    HttpStatus.NOT_FOUND);
        }

        Emotion addedEmotion = emotion.get();
        note.setEmotion(addedEmotion);
        noteService.saveNote(note);

        return new ResponseEntity<>("Note created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{note-id}")
    public ResponseEntity<Object> deleteNoteById(@PathVariable("note-id") Long id) {
        Optional<Note> oldNoteData = noteService.getBookById(id);

        if (oldNoteData.isPresent()) {
            try {
                noteService.deleteNoteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
