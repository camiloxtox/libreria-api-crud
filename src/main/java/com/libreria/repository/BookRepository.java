package com.libreria.repository;

import com.libreria.model.Book;

import java.util.ArrayList; import java.util.Collections; import java.util.List; import java.util.Optional; import java.util.concurrent.atomic.AtomicInteger;

public class BookRepository {

    private static final List<Book> books = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    public static List<Book> getAll() {
        return new ArrayList<>(books); // devolvemos una copia para seguridad
    }

    public static Optional<Book> getById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    public static Book add(Book book) {
        book.setId(autoId.getAndIncrement());
        books.add(book);
        return book;
    }

    public static Optional<Book> update(int id, Book updatedBook) {
        Optional<Book> bookOptional = getById(id);
        if (bookOptional.isPresent()) {
            Book existing = bookOptional.get();
            existing.setTitle(updatedBook.getTitle());
            existing.setAuthor(updatedBook.getAuthor());
            existing.setPublicationYear(updatedBook.getPublicationYear());
            return Optional.of(existing);
        } else {
            return Optional.empty();
        }
    }

    public static boolean delete(int id) {
        return books.removeIf(book -> book.getId() == id);
    }
}