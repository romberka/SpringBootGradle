package com.amwater.microservices.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ActiveProfile {
	@Value("${active.profile.message}")
	private String message;

    public String getActiveProfile() {
    	return message;
    }
}
