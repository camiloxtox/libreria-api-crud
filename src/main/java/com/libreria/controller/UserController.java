package com.libreria.controller;

import com.google.gson.Gson;
import com.libreria.model.User;
import com.libreria.repository.UserRepository;
import spark.Request;
import spark.Response;

public class UserController {
    private static final Gson gson = new Gson();

    public static String getAll(Request req, Response res) {
        res.type("application/json");
        return gson.toJson(UserRepository.getAll());
    }

    public static String getById(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        return UserRepository.getById(id)
                .map(user -> gson.toJson(user))
                .orElseGet(() -> {
                    res.status(404);
                    return "{\"message\":\"User not found\"}";
                });
    }

    public static String add(Request req, Response res) {
        User user = gson.fromJson(req.body(), User.class);
        res.status(201);
        return gson.toJson(UserRepository.add(user));
    }

    public static String update(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        User updated = gson.fromJson(req.body(), User.class);
        return UserRepository.update(id, updated)
                .map(user -> gson.toJson(user))
                .orElseGet(() -> {
                    res.status(404);
                    return "{\"message\":\"User not found\"}";
                });
    }

    public static String delete(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        boolean success = UserRepository.delete(id);
        if (success) return "{\"message\":\"User deleted\"}";
        res.status(404);
        return "{\"message\":\"User not found\"}";
    }
}