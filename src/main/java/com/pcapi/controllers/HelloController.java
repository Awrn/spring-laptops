package com.pcapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Value("${app.varexample}")
    String user;


    @GetMapping("/saludo")
    public String enviarSaludo(){

        return user;

    }

}
