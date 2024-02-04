package com.app.motivation_services.controller;

import com.app.motivation_services.model.Author;
import com.app.motivation_services.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public ResponseEntity<String> createAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
        return new ResponseEntity<>("Note created successfully", HttpStatus.CREATED);
    }
}
