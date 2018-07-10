package com.amwater.microservices.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amwater.microservices.demo.Quote;

@RestController
public class QuoteController {
	private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);
	
	@Autowired
    private RestTemplate restTemplate;

    @GetMapping("/randomQuote")
    public Quote randomQuote() {
    	Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    	
        return quote;
    }
}
