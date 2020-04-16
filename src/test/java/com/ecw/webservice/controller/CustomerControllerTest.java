package com.ecw.webservice.controller;

import com.ecw.webservice.dto.CustomerDTO;
import com.ecw.webservice.dto.CustomerDTOList;
import com.ecw.webservice.service.CustomerService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void findAllCustomer() throws Exception {
        List<CustomerDTO> customerDTOList = Arrays.asList(
                            CustomerDTO.builder().firstName("test").build(),
                            CustomerDTO.builder().firstName("test1").build()
                        );
        Mockito.when(customerService.findAllCustomer()).thenReturn(customerDTOList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String returnStr = mvcResult.getResponse().getContentAsString();
        assertNotNull(returnStr);
    }

    @Test
    void findCustomerById() throws Exception {

        Mockito.when(customerService.findById(Mockito.any())).thenReturn(CustomerDTO.builder().firstName("test").build());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists());
    }

    @Test
    void createCustomer() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().firstName("test").lastName("test").build();
        //given
        Mockito.when(customerService.createCustomer(Mockito.any())).thenReturn(customerDTO);
        ObjectMapper mapper = new ObjectMapper();
        //when
        String responseStr = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/customers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(customerDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").isString())
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        assertNotNull(responseStr);
    }
}