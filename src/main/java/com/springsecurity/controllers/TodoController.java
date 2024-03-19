package com.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
public class TodoController {

    public static final List<Todo> TODOS = List.of(new Todo("urke_jov", "Learn Spring Cloud")
            , new Todo("urke_jov", "Learn AWS"));

    @GetMapping("/todos")
    public List<Todo> retrieveAllTodos() {
        return TODOS;
    }

    @GetMapping("users/{username}/todos")
    public Todo retrieveTodoForUser(@PathVariable String username) {

        Predicate<Todo> predicate = todo -> todo.username().equals(username);
        Optional<Todo> todo =
                TODOS.stream()
                        .filter(predicate)
                        .findFirst();

        return todo.orElse(null);
    }


    private record Todo(String username, String description) {
    }
}
