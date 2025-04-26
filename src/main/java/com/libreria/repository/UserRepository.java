package com.libreria.repository;

import com.libreria.model.User;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {
    private static final List<User> users = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger();

    public static List<User> getAll() { return new ArrayList<>(users); }

    public static Optional<User> getById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    public static User add(User user) {
        user.setId(idCounter.incrementAndGet());
        users.add(user);
        return user;
    }

    public static Optional<User> update(int id, User updated) {
        return getById(id).map(user -> {
            user.setUsername(updated.getUsername());
            user.setEmail(updated.getEmail());
            return user;
        });
    }

    public static boolean delete(int id) {
        return users.removeIf(user -> user.getId() == id);
    }
}