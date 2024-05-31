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

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
    public interface CreateTask {
    }

    public interface UpdateTask {
    }

    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    @NotNull(groups = { CreateTask.class, UpdateTask.class }, message = "Task title is required")
    @Size(groups = { CreateTask.class,
            UpdateTask.class }, min = 2, max = 100, message = "Task title must be between 2 and 100 characters")
    private String title;

    @Column(name = "description", nullable = true, length = 250)
    @NotNull(groups = { CreateTask.class, UpdateTask.class }, message = "Task description is required")
    @Size(groups = { CreateTask.class,
            UpdateTask.class }, max = 250, message = "Task title must be 250 characters or less")
    private String description;

    @Column(name = "date", nullable = false)
    @NotNull(groups = { CreateTask.class, UpdateTask.class }, message = "Task date is required")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Task() {
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
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
