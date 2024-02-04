package com.app.motivation_services.service;

import com.app.motivation_services.model.Author;
import com.app.motivation_services.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    public Optional<Author> getAuthorByAuthorName(String authorName) {
        return authorRepository.findAll()
                .stream()
                .filter(author -> author.getAuthorName().equals(authorName))
                .findFirst();
    }
}
