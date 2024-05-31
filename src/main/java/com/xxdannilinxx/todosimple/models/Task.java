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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = Task.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
}
