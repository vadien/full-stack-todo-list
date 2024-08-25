package io.nology.todos.todo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repo;

    public Todo createTodo(@Valid CreateTodoDTO data) {
        Todo newTodo = new Todo();
        newTodo.setTitle(data.getTitle().trim());
        newTodo.setCategory(data.getCategory());
        newTodo.setArchived(false);
        newTodo.setCreatedAt(new Date());
        newTodo.setUpdatedAt(new Date());
        return this.repo.save(newTodo);
    }

    public List<Todo> findAll() {
        return this.repo.findAll();
    }

    public Optional<Todo> findById(Long id) {
        return this.repo.findById(id);
    }

    public Optional<Todo> updateTodoById(Long id, @Valid UpdateTodoDTO data) {
        // TODO: validation
        Optional<Todo> result = this.findById(id);
        if (result.isEmpty()) {
            return result;
        }
        Todo foundTodo = result.get();
        foundTodo.setTitle(data.getTitle());
        foundTodo.setCategory(data.getCategory());
        foundTodo.setArchived(data.isArchived());
        foundTodo.setUpdatedAt(new Date());

        Todo updatedTodo = this.repo.save(foundTodo);

        return Optional.of(updatedTodo);
    }

    public Optional<Todo> deleteTodoById(Long id) {
        Optional<Todo> result = this.findById(id);
        if (result.isEmpty()) {
            return result;
        }
        this.repo.delete(result.get());
        return result;
    }

}
