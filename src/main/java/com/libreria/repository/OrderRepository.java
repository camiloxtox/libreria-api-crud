package com.libreria.repository;

import com.libreria.model.Order;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderRepository {
    private static final List<Order> orders = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger();

    public static List<Order> getAll() { return new ArrayList<>(orders); }

    public static Optional<Order> getById(int id) {
        return orders.stream().filter(o -> o.getId() == id).findFirst();
    }

    public static Order add(Order order) {
        order.setId(idCounter.incrementAndGet());
        orders.add(order);
        return order;
    }

    public static Optional<Order> update(int id, Order updated) {
        return getById(id).map(order -> {
            order.setDescription(updated.getDescription());
            return order;
        });
    }

    public static boolean delete(int id) {
        return orders.removeIf(order -> order.getId() == id);
    }
}