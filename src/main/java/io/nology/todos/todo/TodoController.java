package io.nology.todos.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("todos")
public class TodoController {

    // autowire service once created
    @Autowired
    private TodoService todoService;

    // CREATE
    @PostMapping
    public ResponseEntity<Todo> postMethodName(@Valid @RequestBody CreateTodoDTO data) {
        Todo createdTodo = this.todoService.createTodo(data);
        return new ResponseEntity<Todo>(createdTodo, HttpStatus.CREATED);
    }

    // READ
    @GetMapping
    public ResponseEntity<List<Todo>> findAllTodos() {
        List<Todo> todoList = this.todoService.findAll();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    // UPDATE
    @PatchMapping
public ResponseEntity<Todo> updateTodoById (@PathVariable Long id, @Valid RequestBody UpdateTodoDTO data) throws Exception {
    Optional<Todo> result = this.todoService.updateTodoById(id, data);
    Todo updatedTodo = result.orElseThrow(() -> new Exception("Could not find post"));
    return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
}

    // DELETE
}
