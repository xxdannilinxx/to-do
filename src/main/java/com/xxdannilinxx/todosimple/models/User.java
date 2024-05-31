package com.xxdannilinxx.todosimple.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = User.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {
    public interface CreateUser {
    }

    public interface UpdateUser {
    }

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    @NotNull(groups = CreateUser.class, message = "Name is required")
    @Size(groups = CreateUser.class, min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @Column(name = "username", nullable = false, length = 100, unique = true, updatable = false)
    @NotNull(groups = CreateUser.class, message = "Username is required")
    @Size(groups = CreateUser.class, min = 5, max = 100, message = "Name must be between 5 and 100 characters")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false, length = 100)
    @NotNull(groups = { CreateUser.class, UpdateUser.class }, message = "Password is required")
    @Size(groups = { CreateUser.class,
            UpdateUser.class }, min = 5, max = 100, message = "Name must be between 5 and 100 characters")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Task> tasks = new ArrayList<>();
}
