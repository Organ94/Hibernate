package com.example.hibernate.controller;

import com.example.hibernate.repository.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/product/fetch-product")
    public List<String> getProductName(@RequestParam String name) {
        return repository.getProductName(name);
    }
}
