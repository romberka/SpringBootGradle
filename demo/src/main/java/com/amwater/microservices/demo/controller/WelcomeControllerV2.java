package com.amwater.microservices.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V2")
public class WelcomeControllerV2 {
	private static final Logger logger = LoggerFactory.getLogger(WelcomeControllerV2.class);
	
	@Value("${welcome.message}")
	private String message;

    @GetMapping({"/","/welcome"})
    public String index() {
    	logger.info("Welcome message version 2 displayed!");
    	
        return message + " This is version 2 of the welcome service.";
    }
}
