package com.libreria.controller;

import com.google.gson.Gson;
import com.libreria.model.Order;
import com.libreria.repository.OrderRepository;
import spark.Request;
import spark.Response;

public class OrderController {
    private static final Gson gson = new Gson();

    public static String getAll(Request req, Response res) {
        res.type("application/json");
        return gson.toJson(OrderRepository.getAll());
    }

    public static String getById(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        return OrderRepository.getById(id)
                .map(order -> gson.toJson(order))
                .orElseGet(() -> {
                    res.status(404);
                    return "{\"message\":\"Order not found\"}";
                });
    }

    public static String add(Request req, Response res) {
        Order order = gson.fromJson(req.body(), Order.class);
        res.status(201);
        return gson.toJson(OrderRepository.add(order));
    }

    public static String update(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        Order updated = gson.fromJson(req.body(), Order.class);
        return OrderRepository.update(id, updated)
                .map(order -> gson.toJson(order))
                .orElseGet(() -> {
                    res.status(404);
                    return "{\"message\":\"Order not found\"}";
                });
    }

    public static String delete(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        boolean success = OrderRepository.delete(id);
        if (success) return "{\"message\":\"Order deleted\"}";
        res.status(404);
        return "{\"message\":\"Order not found\"}";
    }
}
