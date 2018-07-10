package com.amwater.microservices.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amwater.microservices.demo.Email;
import com.amwater.microservices.demo.jms.MessageReceiver;
import com.amwater.microservices.demo.jms.MessageSender;

@RestController
public class JmsMessageController {

	@Autowired
	MessageSender messageSender;
	
	@Autowired
	MessageReceiver messageReceiver;
	
    @GetMapping("/jmsSendReceive")
    public String jmsSendReceive() throws Exception {
        String returnMessage = "";

//        messageSender.sendMessage("New test message");
//        returnMessage = messageReceiver.receiveTextMessage();
        
    	messageSender.sendMessage(new Email("someone@somewhere.com", "Body of message"));
        Email email = (Email) messageReceiver.receiveObjectMessage();
        returnMessage = email.toString();
        
        return returnMessage;
    }
}
