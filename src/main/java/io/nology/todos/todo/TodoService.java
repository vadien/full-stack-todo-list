package io.nology.todos.todo;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.todos.category.Category;
import io.nology.todos.category.CategoryService;
import io.nology.todos.common.ValidationErrors;
import io.nology.todos.common.exceptions.ServiceValidationException;
import jakarta.validation.Valid;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repo;

    @Autowired
    private CategoryService categoryService;

    public Todo createTodo(@Valid CreateTodoDTO data) throws Exception {
        ValidationErrors errors = new ValidationErrors();
        Todo newTodo = new Todo();
        newTodo.setTitle(data.getTitle().trim());
        newTodo.setArchived(false);
        Optional<Category> categoryResult = this.categoryService.findById(data.getCategoryId());
        if (categoryResult.isEmpty()) {
            errors.addError("category", "Selected category does not exist");
        }
        if (!errors.isEmpty()) {
            throw new ServiceValidationException(errors);
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
        ValidationErrors errors = new ValidationErrors();
        Optional<Todo> result = this.findById(id);
        if (result.isEmpty()) {
            errors.addError("id", String.format("Could not find post with id %s", id));
        }
        Todo foundTodo = result.get();
        foundTodo.setTitle(data.getTitle());
        foundTodo.setArchived(data.isArchived());
        Optional<Category> categoryResult = this.categoryService.findById(data.getCategoryId());
        if (categoryResult.isEmpty()) {
            errors.addError("category", "Selected category does not exist");
        }
        if (!errors.isEmpty()) {
            throw new ServiceValidationException(errors);
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
