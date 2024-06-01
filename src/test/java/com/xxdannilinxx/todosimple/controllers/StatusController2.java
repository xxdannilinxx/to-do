package com.xxdannilinxx.todosimple.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.xxdannilinxx.todosimple.models.Task;
import com.xxdannilinxx.todosimple.models.User;
import com.xxdannilinxx.todosimple.services.StatusService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class StatusController2 {

    @Autowired
    private StatusController statusController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StatusService statusService;

    @Test
    public void testGetStatus() {
        ResponseEntity<ArrayList<Task>> actualResponse = this.statusController.getStatus();

        ArrayList<Task> body = actualResponse.getBody();

        assertNotNull(body);
        assertEquals(2, body.size());
    }

    @Test
    public void testGetStatusIntegration() throws Exception {
        User user = new User(1L, "teste", "teste@teste.com", "123456789", new ArrayList<>());

        Task task = new Task(1L, "teste 123", "teste", new java.util.Date(), user);

        ArrayList<Task> tasks = new ArrayList<>(List.of(task));

        when(this.statusService.getStatus()).thenReturn(tasks);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/status/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
