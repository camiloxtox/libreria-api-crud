package com.libreria.repository;

import com.libreria.model.Category;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryRepository {
    private static final List<Category> categories = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger();

    public static List<Category> getAll() { return new ArrayList<>(categories); }

    public static Optional<Category> getById(int id) {
        return categories.stream().filter(c -> c.getId() == id).findFirst();
    }

    public static Category add(Category category) {
        category.setId(idCounter.incrementAndGet());
        categories.add(category);
        return category;
    }

    public static Optional<Category> update(int id, Category updated) {
        return getById(id).map(category -> {
            category.setName(updated.getName());
            return category;
        });
    }

    public static boolean delete(int id) {
        return categories.removeIf(category -> category.getId() == id);
    }
}