package com.app.motivation_services.service;

import com.app.motivation_services.model.Note;
import com.app.motivation_services.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public void deleteNoteById(Long id) throws Exception {
        try {
            noteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Note with ID " + id + " not found");
        }
    }
}
