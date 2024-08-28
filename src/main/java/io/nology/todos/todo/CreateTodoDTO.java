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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // TODO: dueAt

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
