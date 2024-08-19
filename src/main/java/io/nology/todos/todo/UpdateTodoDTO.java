package io.nology.todos.todo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateTodoDTO {

    @NotBlank
    @Length(min = 1, max = 255)
    private String title;

    @NotBlank
    @Length(min = 1, max = 255)
    private String category;

    @NotNull
    private boolean isArchived;

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public boolean isArchived() {
        return isArchived;
    }

}
