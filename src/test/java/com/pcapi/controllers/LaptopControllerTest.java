package com.pcapi.controllers;

import com.pcapi.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);


    }




    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptop = Arrays.asList(response.getBody());
        System.out.println("findall " + laptop.size());
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/api/laptop/1",Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String jSon = """
                {
                                
                 "brand": "MSI",
                 "model": "UltraGamingSeries",
                 "year": 2020,
                 "price": 425.99,
                 "ram": 32,
                 "batterycapacity": 5600,
                 "autonomy": 30,
                 "screenSize": 24.0,
                 "disccapacity": 3500
                    }
                """;

        HttpEntity<String> request = new HttpEntity<>(jSon,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.POST,request,Laptop.class);
        Laptop result = response.getBody();
        assertEquals(1L,result.getId());
        assertEquals("MSI",result.getBrand());
        System.out.println("id " + result.getId());
        System.out.println("name " + result.getBrand());
    }

    @Test
    void update() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String jSon = """
                {
                 "id": 1,              
                 "brand": "New",
                 "model": "UltraGamingSeries",
                 "price": 425.99,
                 "ram": 32,
                 "batterycapacity": 5600,
                 "autonomy": 30,
                 "screenSize": 24.0,
                 "disccapacity": 3500
                    }
                """;

        HttpEntity<String> request = new HttpEntity<>(jSon,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.PUT,request,Laptop.class);
        Laptop result = response.getBody();
        assertEquals(1L,result.getId());
        assertEquals("New",result.getBrand());
        System.out.println(result.getBrand());
        System.out.println("year " + result.getYear());



    }


    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {


    }
}