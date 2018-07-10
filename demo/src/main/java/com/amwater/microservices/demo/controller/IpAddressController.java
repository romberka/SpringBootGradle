package com.amwater.microservices.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amwater.microservices.demo.IpAddress;

@RestController
public class IpAddressController {

	@Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ipAddress")
    public IpAddress ipAddress() throws Exception {
        return getIpAddress();
    }

	private IpAddress getIpAddress() throws Exception {
        return restTemplate.getForObject("http://ip.jsontest.com/", IpAddress.class);
    }
}
