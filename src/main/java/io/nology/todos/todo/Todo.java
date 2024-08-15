package io.nology.todos.todo;

import java.util.Date;

import io.nology.todos.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "todo_list")
public class Todo extends BaseEntity {

    public Todo() {
    }

    @Column
    private String title;

    @Column
    private String category;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDueAt() {
        return dueAt;
    }

    public void setDueAt(Date dueAt) {
        this.dueAt = dueAt;
    }

}
