package com.xxdannilinxx.todosimple.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xxdannilinxx.todosimple.log.Log;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/others")
@Tag(name = "Others", description = "Others for api")
public class OthersController {

    @Log
    @GetMapping
    public String get() throws InterruptedException {
        // AOP - Log
        // Não funciona em meotodos privados

        // Teste de threads virtuais
        Thread.sleep(3000);
        return "others";
    }
}
