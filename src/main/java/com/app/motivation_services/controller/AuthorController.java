package com.app.motivation_services.controller;

import com.app.motivation_services.model.Author;
import com.app.motivation_services.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> allAuthors = authorService.getAllAuthors();
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    @PostMapping("/author")
    public ResponseEntity<String> createAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
        return new ResponseEntity<>("Note created successfully", HttpStatus.CREATED);
    }
}
