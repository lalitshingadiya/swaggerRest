package com.ecw.webservice.service;


import com.ecw.webservice.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAllCustomer();

    CustomerDTO findById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    CustomerDTO replaceCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(CustomerDTO customerDTO);

    void deleteById(Long id);
}
