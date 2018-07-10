package com.amwater.microservices.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) // @RunWith(SpringRunner.class) tells JUnit to run using Spring’s testing support. 
@SpringBootTest // The @SpringBootTest annotation tells Spring Boot to go and look for a main configuration class (one with @SpringBootApplication for instance), and use that to start a Spring application context.
@ActiveProfiles("dev")
@TestPropertySource(properties="jasypt.encryptor.password=SB154atw")
public class ContractAccountServiceTest {

	@Autowired
	private ContractAccountService contractAccountService;
	
	@Test
	public void testAccount() throws Exception {
		assertThat(this.contractAccountService).isNotNull();
		
		Account account = contractAccountService.getAccount("220008979171");
		
		assertThat(account.getCustomerNumber().equalsIgnoreCase("1200184065"));
    }
}