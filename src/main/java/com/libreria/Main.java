package com.libreria;

import static spark.Spark.*;
import com.libreria.controller.*;

public class Main {
    public static void main(String[] args) {
        port(4567);

        // CRUD: Book
        path("/books", () -> {
            get("", BookController::getAll);
            get("/:id", BookController::getById);
            post("", BookController::add);
            put("/:id", BookController::update);
            delete("/:id", BookController::delete);
        });

        // CRUD: Author
        path("/authors", () -> {
            get("", AuthorController::getAll);
            get("/:id", AuthorController::getById);
            post("", AuthorController::add);
            put("/:id", AuthorController::update);
            delete("/:id", AuthorController::delete);
        });

        // CRUD: Editorial
        path("/editorials", () -> {
            get("", EditorialController::getAll);
            get("/:id", EditorialController::getById);
            post("", EditorialController::add);
            put("/:id", EditorialController::update);
            delete("/:id", EditorialController::delete);
        });

        // CRUD: User
        path("/users", () -> {
            get("", UserController::getAll);
            get("/:id", UserController::getById);
            post("", UserController::add);
            put("/:id", UserController::update);
            delete("/:id", UserController::delete);
        });

        // CRUD: Category
        path("/categories", () -> {
            get("", CategoryController::getAll);
            get("/:id", CategoryController::getById);
            post("", CategoryController::add);
            put("/:id", CategoryController::update);
            delete("/:id", CategoryController::delete);
        });

        // CRUD: Order
        path("/orders", () -> {
            get("", OrderController::getAll);
            get("/:id", OrderController::getById);
            post("", OrderController::add);
            put("/:id", OrderController::update);
            delete("/:id", OrderController::delete);
        });

        System.out.println("Servidor corriendo en http://localhost:4567/");
    }
}