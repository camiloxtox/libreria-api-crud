package com.libreria.controller;

import com.google.gson.Gson;
import com.libreria.model.Editorial;
import com.libreria.repository.EditorialRepository;
import spark.Request;
import spark.Response;

public class EditorialController {
    private static final Gson gson = new Gson();

    public static String getAll(Request req, Response res) {
        res.type("application/json");
        return gson.toJson(EditorialRepository.getAll());
    }

    public static String getById(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        return EditorialRepository.getById(id)
                .map(editorial -> gson.toJson(editorial))
                .orElseGet(() -> {
                    res.status(404);
                    return "{\"message\":\"Editorial not found\"}";
                });
    }

    public static String add(Request req, Response res) {
        Editorial editorial = gson.fromJson(req.body(), Editorial.class);
        res.status(201);
        return gson.toJson(EditorialRepository.add(editorial));
    }

    public static String update(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        Editorial updated = gson.fromJson(req.body(), Editorial.class);
        return EditorialRepository.update(id, updated)
                .map(editorial -> gson.toJson(editorial))
                .orElseGet(() -> {
                    res.status(404);
                    return "{\"message\":\"Editorial not found\"}";
                });
    }

    public static String delete(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        boolean success = EditorialRepository.delete(id);
        if (success) return "{\"message\":\"Editorial deleted\"}";
        res.status(404);
        return "{\"message\":\"Editorial not found\"}";
    }
}