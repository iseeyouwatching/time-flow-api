package ru.hits.timeflowapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {

    @GetMapping("/unsecured")
    public String sayHelloWorld() {
        return "Незащищенный эндпоинт.";
    }

    @GetMapping("/secured")
    public String sayHelloSecured() {
        return "Защищенный эндпоинт.";
    }

}
