package com.ecw.webservice.mapper;

import com.ecw.webservice.dto.CustomerDTO;
import com.ecw.webservice.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    CustomerMapper customerMapper;
    @BeforeEach
    void setUp() {
        customerMapper = CustomerMapper.INSTANCE;
    }

    @Test
    void toCustomerDTO() {
        //given
        Customer customer = Customer.builder().firstName("test").build();

        //when
        CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);

        //then
        assertNotNull(customerDTO);
        assertEquals(customer.getFirstName(),customerDTO.getFirstName());
    }



    @Test
    void toCustomer() {
        //given
        CustomerDTO customerDTO = CustomerDTO.builder().firstName("test").build();

        //when
        Customer customer = customerMapper.toCustomer(customerDTO);

        //then
        assertNotNull(customer);
        assertEquals(customerDTO.getFirstName(),customer.getFirstName());
    }
}