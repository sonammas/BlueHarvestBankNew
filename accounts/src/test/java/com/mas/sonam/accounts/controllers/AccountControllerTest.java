package com.mas.sonam.accounts.controllers;

import com.mas.sonam.accounts.controller.AccountController;
import com.mas.sonam.accounts.model.dto.CustomerDto;
import com.mas.sonam.accounts.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@EnableSpringDataWebSupport
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private CustomerDto customerDto = new CustomerDto();

    @Before
    public void setUp() {
        customerDto.setBalance(new BigDecimal(2));
        customerDto.setSurname("surname");
        customerDto.setName("name");
    }

    @Test
    public void openSecondaryAccountForCustomer_returnok() throws Exception {
        given(accountService.openSecondaryAccountForCustomer(1L, new BigDecimal(10L)))
                .willReturn(2L);

        mockMvc.perform(post("/account/1/open/10")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getTransactionForCustomer_returnCustomerDto() throws Exception {
        given(accountService.getTransactionForCustomer(1L, "PRIMARY")).willReturn(customerDto);

        mockMvc.perform(get("/account/1/transactions/type/PRIMARY")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name", is("sonam")));
    }
}
