package com.decagon.springmvcsq019.controllers;

import com.decagon.springmvcsq019.repositories.OrderRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private OrderRepositories orderRepositories;


    @Autowired
    public AuthController(OrderRepositories orderRepositories) {
        this.orderRepositories = orderRepositories;
    }

    @GetMapping
    public String index(){

        System.out.println(orderRepositories.findAll());

        return "index";
    }
}
