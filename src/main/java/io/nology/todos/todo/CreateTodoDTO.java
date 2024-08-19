package io.nology.todos.todo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class CreateTodoDTO {
    @NotBlank
    @Length(min = 1, max = 255)
    private String title;

    // TODO: change to cat ID.
    @NotBlank
    @Length(min = 1, max = 255)
    private String category;

    // TODO: dueAt

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

}
