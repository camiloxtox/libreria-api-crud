package com.libreria.controller;

import com.google.gson.Gson;
import com.libreria.model.Author;
import com.libreria.repository.AuthorRepository;
import spark.Request;
import spark.Response;

public class AuthorController {
    private static final Gson gson = new Gson();

    public static String getAll(Request req, Response res) {
        res.type("application/json");
        return gson.toJson(AuthorRepository.getAll());
    }

    public static String getById(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        return AuthorRepository.getById(id)
                .map(author -> gson.toJson(author))
                .orElseGet(() -> {
                    res.status(404);
                    return "{\"message\":\"Author not found\"}";
                });
    }

    public static String add(Request req, Response res) {
        Author author = gson.fromJson(req.body(), Author.class);
        res.status(201);
        return gson.toJson(AuthorRepository.add(author));
    }

    public static String update(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        Author updated = gson.fromJson(req.body(), Author.class);
        return AuthorRepository.update(id, updated)
                .map(author -> gson.toJson(author))
                .orElseGet(() -> {
                    res.status(404);
                    return "{\"message\":\"Author not found\"}";
                });
    }

    public static String delete(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        boolean success = AuthorRepository.delete(id);
        if (success) return "{\"message\":\"Author deleted\"}";
        res.status(404);
        return "{\"message\":\"Author not found\"}";
    }
}
