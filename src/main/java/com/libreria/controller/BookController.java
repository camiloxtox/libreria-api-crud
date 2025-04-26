package com.libreria.controller;

import com.google.gson.Gson; import com.libreria.model.Book; import com.libreria.repository.BookRepository; import spark.Request; import spark.Response;

import java.util.Optional;

public class BookController {

    private static final Gson gson = new Gson();

    public static String getAll(Request req, Response res) {
        res.type("application/json");
        return gson.toJson(BookRepository.getAll());
    }

    public static String getById(Request req, Response res) {
        res.type("application/json");
        int id = Integer.parseInt(req.params(":id"));
        Optional<Book> book = BookRepository.getById(id);
        if (book.isPresent()) {
            return gson.toJson(book.get());
        } else {
            res.status(404);
            return "{\"message\":\"Book not found\"}";
        }
    }

    public static String add(Request req, Response res) {
        res.type("application/json");
        Book newBook = gson.fromJson(req.body(), Book.class);
        Book added = BookRepository.add(newBook);
        res.status(201);
        return gson.toJson(added);
    }

    public static String update(Request req, Response res) {
        res.type("application/json");
        int id = Integer.parseInt(req.params(":id"));
        Book updatedBook = gson.fromJson(req.body(), Book.class);
        Optional<Book> result = BookRepository.update(id, updatedBook);
        if (result.isPresent()) {
            return gson.toJson(result.get());
        } else {
            res.status(404);
            return "{\"message\":\"Book not found\"}";
        }
    }

    public static String delete(Request req, Response res) {
        res.type("application/json");
        int id = Integer.parseInt(req.params(":id"));
        boolean deleted = BookRepository.delete(id);
        if (deleted) {
            return "{\"message\":\"Book deleted\"}";
        } else {
            res.status(404);
            return "{\"message\":\"Book not found\"}";
        }
    }
}