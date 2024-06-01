package com.xxdannilinxx.todosimple.services;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xxdannilinxx.todosimple.models.Task;
import com.xxdannilinxx.todosimple.models.User;
import com.xxdannilinxx.todosimple.repositories.TaskRepository;
import com.xxdannilinxx.todosimple.services.exceptions.ObjectNotFoundException;

@Service
public class StatusService {

    public static final String API_URL = "https://6058a452c3f49200173ae955.mockapi.io/api/v1";

    @Autowired
    private RestTemplate restTemplate;

    public StatusService() {
    }

    public ArrayList<Task> getStatus() {
        String url = API_URL + "/task";

        ResponseEntity<ArrayList<Task>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<ArrayList<Task>>() {
                });

        return response.getBody();
    }
}
