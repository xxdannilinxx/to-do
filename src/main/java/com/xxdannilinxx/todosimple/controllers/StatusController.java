package com.xxdannilinxx.todosimple.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.xxdannilinxx.todosimple.models.Task;
import com.xxdannilinxx.todosimple.services.StatusService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/status")
@Tag(name = "Status", description = "Status for api")
public class StatusController {

    @Autowired
    private StatusService statusService;

    public static final String API_URL = "https://6058a452c3f49200173ae955.mockapi.io/api/v1";

    @GetMapping("/")
    @Operation(summary = "Fetch status", description = "Returns status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<ArrayList<Task>> getStatus() {
        ArrayList<Task> status = statusService.getStatus();

        return ResponseEntity.ok().body(status);
    }
}
