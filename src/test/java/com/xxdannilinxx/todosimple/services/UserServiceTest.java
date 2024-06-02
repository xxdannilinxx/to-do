package com.xxdannilinxx.todosimple.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.xxdannilinxx.todosimple.models.User;
import com.xxdannilinxx.todosimple.repositories.UserRepository;
import com.xxdannilinxx.todosimple.services.exceptions.ObjectNotFoundException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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

    @Test
    public void testeCreateSucessNotMocks() {
        User u = new User(0L, "teste", "teste@teste.com", "123456789", new ArrayList<>());

        this.userService.create(u);

        User result = userRepository.findById(u.getId()).orElse(null);
        assertNotNull(result.getId());
        assertEquals(u.getName(), result.getName());

        userService.delete(u.getId());
    }
}
