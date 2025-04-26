package com.libreria.repository;

import com.libreria.model.Editorial;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EditorialRepository {
    private static final List<Editorial> editorials = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger();

    public static List<Editorial> getAll() { return new ArrayList<>(editorials); }

    public static Optional<Editorial> getById(int id) {
        return editorials.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Editorial add(Editorial editorial) {
        editorial.setId(idCounter.incrementAndGet());
        editorials.add(editorial);
        return editorial;
    }

    public static Optional<Editorial> update(int id, Editorial updated) {
        return getById(id).map(editorial -> {
            editorial.setName(updated.getName());
            editorial.setCountry(updated.getCountry());
            return editorial;
        });
    }

    public static boolean delete(int id) {
        return editorials.removeIf(editorial -> editorial.getId() == id);
    }
}