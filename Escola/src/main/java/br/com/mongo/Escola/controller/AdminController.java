package br.com.mongo.Escola.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/hello")
    public String  index() {
       return "index";
    }




}
