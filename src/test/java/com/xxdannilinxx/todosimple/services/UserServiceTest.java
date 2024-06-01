package com.xxdannilinxx.todosimple.services;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xxdannilinxx.todosimple.models.User;
import com.xxdannilinxx.todosimple.repositories.UserRepository;
import com.xxdannilinxx.todosimple.services.exceptions.ObjectNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;

    User user;

    @BeforeAll
    public static void start() {
        System.out.println("start");
    }

    @AfterAll
    public static void end() {
        System.out.println("end");
    }

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("test");
        user.setUsername("tester");
        user.setPassword("123456");
    }

    @AfterEach
    public void setDown() {
        user = null;
    }

    @Test
    public void testFindByIdSuccess() {
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        User result = service.findById(user.getId());

        assertEquals(user, result);
        verify(repository).findById(user.getId());
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testFindByIdNotFound() {
        final Long id = Long.valueOf(10);

        when(repository.findById(id)).thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> service.findById(id));

        assertTrue(exception.getMessage().contains("User not found"));
        verify(repository).findById(id);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testFindByIdNotId() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.findById(null));

        assertTrue(exception.getMessage().contains("id cannot be null"));
        verifyNoInteractions(repository);
    }

    @Test
    public void testeCreateSuccess() {
        when(repository.save(user)).thenReturn(user);

        User result = service.create(user);

        assertEquals(user, result);
        verify(repository).save(user);
        verifyNoMoreInteractions(repository);
    }
}
