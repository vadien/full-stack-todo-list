package io.nology.todos.todo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateTodoDTO {

    @NotBlank
    @Length(min = 1, max = 255)
    private String title;

    @NotNull
    @Min(1)
    private Long categoryId;

    @NotNull
    private Boolean isArchived;

    public String getTitle() {
        return title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Boolean isArchived() {
        return isArchived;
    }

}
