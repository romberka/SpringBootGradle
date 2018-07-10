package com.amwater.microservices.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
    @GetMapping("/product/{product}")
    public String product(@PathVariable("product") String product) {
    	return "Product: " + product;
    }
}
