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
    private Boolean archived;

    public String getTitle() {
        return title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Boolean getArchived() {
        return archived;
    }

    @Override
    public String toString() {
        return "UpdateTodoDTO [title=" + title + ", categoryId=" + categoryId + ", getArchived=" + archived + "]";
    }

}
