package com.xxdannilinxx.todosimple.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
    public interface Create {
    }

    public interface Update {
    }

    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @NotNull(groups = { Create.class, Update.class }, message = "Task id is required")
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    @NotNull(groups = { Create.class, Update.class }, message = "Task title is required")
    @Size(groups = { Create.class,
            Update.class }, min = 2, max = 100, message = "Task title must be between 2 and 100 characters")
    private String title;

    @Column(name = "description", nullable = true, length = 250)
    @NotNull(groups = { Create.class, Update.class }, message = "Task description is required")
    @Size(groups = { Create.class, Update.class }, max = 250, message = "Task title must be 250 characters or less")
    private String description;

    @Column(name = "date", nullable = false, updatable = false)
    @NotNull(groups = { Create.class, Update.class }, message = "Task date is required")
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task(Long id, String title, String description, String date, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title)
                && Objects.equals(description, task.description) && Objects.equals(date, task.date)
                && Objects.equals(user, task.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, date, user);
    }
}
