package com.spring_grahql.controller;

import com.spring_grahql.model.Author;
import com.spring_grahql.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @QueryMapping
    Iterable<Author> authors() {
        return authorRepository.findAll();
    }
}
