package io.nology.todos.todo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import java.util.Optional;

import io.nology.todos.category.CategoryService;
import io.nology.todos.common.exceptions.ServiceValidationException;

public class TodoServiceUnitTests {
    @Mock
    private TodoRepository repo;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    @Spy
    private TodoService todoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTodo_fails_categoryNotFound() {
        // given
        CreateTodoDTO data = new CreateTodoDTO();
        data.setTitle("data");
        data.setCategoryId(2L);

        // when
        when(this.categoryService.findById(data.getCategoryId())).thenReturn(Optional.empty());
        // then
        assertThrows(ServiceValidationException.class, () -> todoService.createTodo(data));

        verify(repo, never()).save(any());
    }

    @Test
    void updateTodo_fails_todoNotFound() {
        UpdateTodoDTO data = new UpdateTodoDTO();
        Long testId = 2L;

        when(this.todoService.findById(testId)).thenReturn(Optional.empty());

        assertThrows(ServiceValidationException.class, () -> todoService.updateTodoById(testId, data));

        verify(repo, never()).save(any());
    }

}
