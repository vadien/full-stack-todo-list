package io.nology.todos.todo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.nology.todos.category.Category;
import io.nology.todos.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "todo_list")
public class Todo extends BaseEntity {

    public Todo() {
    }

    @NotBlank
    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("todos")
    private Category category;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueAt;

    @Column
    private Boolean completed;

    @Column
    private Boolean archived;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDueAt() {
        return dueAt;
    }

    public void setDueAt(Date dueAt) {
        this.dueAt = dueAt;
    }

    public boolean getArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

}
