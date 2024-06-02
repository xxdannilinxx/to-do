package com.xxdannilinxx.todosimple.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/others")
@Tag(name = "Others", description = "Others for api")
public class OthersController {

    // teste de threads virtuais, mas somente no java ^19
    @GetMapping
    public String get() throws InterruptedException {
        Thread.sleep(3000);
        return "others";
    }
}
