package com.libreria.controller;

import com.google.gson.Gson;
import com.libreria.model.Category;
import com.libreria.repository.CategoryRepository;
import spark.Request;
import spark.Response;

public class CategoryController {
    private static final Gson gson = new Gson();

    public static String getAll(Request req, Response res) {
        res.type("application/json");
        return gson.toJson(CategoryRepository.getAll());
    }

    public static String getById(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        return CategoryRepository.getById(id)
                .map(category -> gson.toJson(category))
                .orElseGet(() -> {
                    res.status(404);
                    return "{\"message\":\"Category not found\"}";
                });
    }

    public static String add(Request req, Response res) {
        Category category = gson.fromJson(req.body(), Category.class);
        res.status(201);
        return gson.toJson(CategoryRepository.add(category));
    }

    public static String update(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        Category updated = gson.fromJson(req.body(), Category.class);
        return CategoryRepository.update(id, updated)
                .map(category -> gson.toJson(category))
                .orElseGet(() -> {
                    res.status(404);
                    return "{\"message\":\"Category not found\"}";
                });
    }

    public static String delete(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        boolean success = CategoryRepository.delete(id);
        if (success) return "{\"message\":\"Category deleted\"}";
        res.status(404);
        return "{\"message\":\"Category not found\"}";
    }
}