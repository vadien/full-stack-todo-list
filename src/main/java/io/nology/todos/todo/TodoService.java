package io.nology.todos.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.todos.category.Category;
import io.nology.todos.category.CategoryService;
import jakarta.validation.Valid;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repo;

    @Autowired
    private CategoryService categoryService;

    public Todo createTodo(@Valid CreateTodoDTO data) throws Exception {
        Todo newTodo = new Todo();
        newTodo.setTitle(data.getTitle().trim());
        newTodo.setArchived(false);
        Optional<Category> categoryResult = this.categoryService.findById(data.getCategoryId());
        if (categoryResult.isEmpty()) {
            throw new Exception("Category does not exist");
        }
        newTodo.setCategory(categoryResult.get());
        return this.repo.save(newTodo);
    }

    public List<Todo> findAll() {
        return this.repo.findAll();
    }

    public Optional<Todo> findById(Long id) {
        return this.repo.findById(id);
    }

    public Optional<Todo> updateTodoById(Long id, @Valid UpdateTodoDTO data) throws Exception {
        Optional<Todo> result = this.findById(id);
        if (result.isEmpty()) {
            return result;
        }
        Todo foundTodo = result.get();
        foundTodo.setTitle(data.getTitle());
        foundTodo.setArchived(data.isArchived());
        Optional<Category> categoryResult = this.categoryService.findById(data.getCategoryId());
        if (categoryResult.isEmpty()) {
            throw new Exception("Category does not exist");
        }
        foundTodo.setCategory(categoryResult.get());
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
