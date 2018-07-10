package com.amwater.microservices.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.amwater.microservices.demo.controller.CxfController;

@RunWith(SpringRunner.class)
@WebMvcTest(CxfController.class)
@AutoConfigureMockMvc(secure = false)
@ActiveProfiles("dev")
public class CxfControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContractAccountService contractAccountServiceMock;
	
	@Test
	public void testAccount() throws Exception {
		assertThat(this.contractAccountServiceMock).isNotNull();
		
		Account account = new Account();
		account.setAccountClassDescription("Mock test");
		account.setCustomerType("P");
		account.setFirstName("John");
		account.setLastName("Doe");
		
		when(contractAccountServiceMock.getAccount(anyString())).thenReturn(account);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/account/1").accept(new MediaType("application", "json")))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.accountClassDescription", is("Mock test")))
			.andExpect(jsonPath("$.formattedFullName", is("John Doe")))
			.andDo(print());
    }
}