package com.pcapi.controllers;

import com.pcapi.entities.Laptop;
import com.pcapi.repositories.LaptopRepo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController  {

    //Logger de errores o info para la terminal
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    //Atributos
    private LaptopRepo laptopRepo;

    //Contructor
    public LaptopController(LaptopRepo laptoprepo) {
        this.laptopRepo = laptoprepo;
    }



    //Metodos
    //1.findAll

    @GetMapping("/api/laptops")
    public List<Laptop> findAll(@RequestHeader HttpHeaders headers,HttpServletRequest request) {
        System.out.println(request.getLocalAddr());
        log.info("finding all laptops-> "+ "from: " + headers.get("User-Agent") + " IP: " + request.getLocalAddr() );
        return laptopRepo.findAll();
    }

    //findById

    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar un laptop por clave primaria Id Long")
    public ResponseEntity<Laptop> findOneById(@ApiParam("Clave primaria tipo Long")@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        log.info("finding a laptop by id -> "+ "from: " + headers.get("User-Agent"));
        Optional<Laptop> laptopOpt = laptopRepo.findById(id);

        if(laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }


    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        log.info("creating a laptop-> "+ "from: " + headers.get("User-Agent"));

        if(laptop.getId() != null){

            log.warn("Trying to create a laptop with Id-> "+ "from: " + headers.get("User-Agent"));
            return ResponseEntity.badRequest().build();

        }

       Laptop result = laptopRepo.save(laptop);
       return ResponseEntity.ok(result);
    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop,@RequestHeader HttpHeaders headers){
        log.info("Updating a laptop by id-> "+ "from: " + headers.get("User-Agent"));

        if(laptop.getId() == null){
            log.warn("Trying to update a non existing laptop-> "+ "from: " + headers.get("User-Agent"));
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepo.existsById(laptop.getId())){

            log.warn("Trying to update a non existing laptop-> "+ "from: " + headers.get("User-Agent"));
            return ResponseEntity.notFound().build();

        }

        Laptop result = laptopRepo.save(laptop);
        return ResponseEntity.ok(result);

    }

    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> deleteById(@PathVariable Long id,@RequestHeader HttpHeaders headers) {
        log.info("Deleting laptop by Id-> "+ "from: " + headers.get("User-Agent"));

        if(!laptopRepo.existsById(id)){

            log.warn("Trying to delete a non existing laptop-> "+ "from: " + headers.get("User-Agent"));
            return ResponseEntity.notFound().build();

        }

        laptopRepo.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(@RequestHeader HttpHeaders headers) {
        log.info("Deleting all laptops-> "+ "from: " + headers.get("User-Agent"));

        laptopRepo.deleteAll();
        return ResponseEntity.noContent().build();

    }



}
