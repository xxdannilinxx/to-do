package com.xxdannilinxx.todosimple.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.notNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xxdannilinxx.todosimple.models.Task;
import com.xxdannilinxx.todosimple.models.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @Test
    public void testFindById() {
        Task task = this.taskService.findById(1L);

        assertEquals("teste 123", task.getTitle());
    }

    @Test
    public void testCreateSuccess() {
        List<Task> tasks = new ArrayList<>();

        Task task = new Task();
        task.setTitle("teste 456");
        task.setDescription("Fazer até xxxx");
        task.setDate(new java.util.Date());

        User user = new User(1L, "teste", "teste@teste.com", "123456789", tasks);
        task.setUser(user);

        Task create = this.taskService.create(task);

        assertNotNull(create.getId());
        assertEquals("teste 456", create.getTitle());
    }
}
