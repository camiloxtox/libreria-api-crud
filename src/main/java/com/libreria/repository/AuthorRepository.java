package com.libreria.repository;

import com.libreria.model.Author;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AuthorRepository {
    private static final List<Author> authors = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger();

    public static List<Author> getAll() { return new ArrayList<>(authors); }

    public static Optional<Author> getById(int id) {
        return authors.stream().filter(a -> a.getId() == id).findFirst();
    }

    public static Author add(Author author) {
        author.setId(idCounter.incrementAndGet());
        authors.add(author);
        return author;
    }

    public static Optional<Author> update(int id, Author updated) {
        return getById(id).map(author -> {
            author.setName(updated.getName());
            author.setNationality(updated.getNationality());
            return author;
        });
    }

    public static boolean delete(int id) {
        return authors.removeIf(author -> author.getId() == id);
    }
}