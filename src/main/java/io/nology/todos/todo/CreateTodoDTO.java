package io.nology.todos.todo;

import org.hibernate.validator.constraints.Length;

import io.nology.todos.category.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

public class CreateTodoDTO {
    @NotBlank
    @Length(min = 1, max = 255)
    private String title;

    // @NotBlank
    @Length(min = 1, max = 255)
    private String category;

    // TODO: dueAt

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
