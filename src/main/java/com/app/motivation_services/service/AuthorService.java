package com.app.motivation_services.service;

import com.app.motivation_services.model.Author;
import com.app.motivation_services.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository =  authorRepository;
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
}
