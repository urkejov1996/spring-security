package com.springsecurity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
public class TodoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

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

    @PostMapping("users/{username}/todos")
    public void createTodoForUser(@PathVariable String username, @RequestBody Todo todo) {
        logger.info("Create {} for {}", todo, username);
    }


    private record Todo(String username, String description) {
    }
}
