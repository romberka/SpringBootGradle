package com.amwater.microservices.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amwater.microservices.demo.IpAddress;

@RestController
public class PostIpAddressController {
	private static final Logger logger = LoggerFactory.getLogger(PostIpAddressController.class);
	
	@Autowired
    private RestTemplate restTemplate;

    @PostMapping("/postIpAddress")
    public String postIpAddress(HttpServletRequest request) throws Exception {
    	String ipAddress = request.getParameter("ipAddress");
    	
    	HttpEntity<IpAddress> httpEntity = new HttpEntity<>(new IpAddress(ipAddress));
    	ResponseEntity<IpAddress> responseEntity = restTemplate.exchange("http://httpbin.org/post", HttpMethod.POST, httpEntity, IpAddress.class);

        return "Posted IP Address: " + ipAddress + "  Returned Status Code: " + responseEntity.getStatusCodeValue();
    }
}
