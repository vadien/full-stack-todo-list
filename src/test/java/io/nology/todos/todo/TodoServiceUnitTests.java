package io.nology.todos.todo;

import static org.hamcrest.Matchers.empty;
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

import io.nology.todos.category.Category;
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

}
