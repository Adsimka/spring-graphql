package com.spring_grahql.controller;

import com.spring_grahql.exception.AuthorNotFoundException;
import com.spring_grahql.model.Author;
import com.spring_grahql.model.Book;
import com.spring_grahql.model.BookInput;
import com.spring_grahql.repository.AuthorRepository;
import com.spring_grahql.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @QueryMapping
    Iterable<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    Optional<Author> authorById(@Argument Long id) {
        return authorRepository.findById(id);
    }

    @MutationMapping
    public Book addBook(@Argument BookInput book) {
        Author author = authorRepository.findById(book.authorId())
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id" + book.authorId()));
        Book buildBook = Book.builder()
                .title(book.title())
                .publisher(book.publisher())
                .author(author)
                .build();

        return bookRepository.save(buildBook);
    }
}
