package com.amwater.microservices.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amwater.microservices.demo.ActiveProfile;

@RestController
public class ActiveProfileController {
	private static final Logger logger = LoggerFactory.getLogger(ActiveProfileController.class);
	
	@Autowired
	private ActiveProfile activeProfile;
	
    @GetMapping("/activeProfile")
    public String activeProfile() {
        return activeProfile.getActiveProfile();
    }
}
