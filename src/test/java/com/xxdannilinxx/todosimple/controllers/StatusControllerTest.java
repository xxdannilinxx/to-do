package com.xxdannilinxx.todosimple.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.xxdannilinxx.todosimple.models.Task;
import com.xxdannilinxx.todosimple.services.StatusService;
import com.xxdannilinxx.todosimple.services.UserService;

@SpringBootTest
public class StatusControllerTest {

    @Mock
    private StatusService statusService;

    @InjectMocks
    private StatusController statusController;

    @Autowired
    private StatusService svc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCallExternal() {
        ArrayList<Task> actualResponse = this.svc.getStatus();

        assertNotNull(actualResponse);
        assertEquals(2, actualResponse.size());
    }

    @Test
    public void testCallExternalWithMock() {
        ArrayList<Task> expectedResponse = new ArrayList<>();

        when(this.statusService.getStatus()).thenReturn(expectedResponse);

        ResponseEntity<ArrayList<Task>> actualResponse = this.statusController.getStatus();

        assertEquals(expectedResponse, actualResponse.getBody());
        verify(this.statusService, times(1)).getStatus();
    }

}
