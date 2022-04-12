package com.pcapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/saludo")
    public String enviarSaludo(){

        return "Hello users!";

    }

}
