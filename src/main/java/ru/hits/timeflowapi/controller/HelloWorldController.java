package ru.hits.timeflowapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String sayHelloWorld() {
        return "Hello, World!";
    }

}
