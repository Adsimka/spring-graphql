package com.spring_grahql.model;

public record BookInput(String title,
                        String publisher,
                        Long authorId) {
}
