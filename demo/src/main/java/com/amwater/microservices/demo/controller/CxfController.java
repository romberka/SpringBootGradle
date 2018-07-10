package com.amwater.microservices.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amwater.microservices.demo.Account;
import com.amwater.microservices.demo.ContractAccountService;

@RestController
public class CxfController {
	private static final Logger logger = LoggerFactory.getLogger(CxfController.class);
	
	@Autowired
    private ContractAccountService contractAccountService;

    @GetMapping("/account/{accountNumber}")
    public Account account(@PathVariable("accountNumber") String accountNumber) {
    	Account account = contractAccountService.getAccount(accountNumber);
    	
        return account;
    }
}
