package com.amwater.microservices.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amwater.microservices.demo.Email;

@RestController
public class JmsController {
	private static final Logger logger = LoggerFactory.getLogger(JmsController.class);
	
	@Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/sendEmailQueue")
    public void sendEmailQueue() {
    	System.out.println("Sending an email message.");
    	
        jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));
        
        return;
    }
}
