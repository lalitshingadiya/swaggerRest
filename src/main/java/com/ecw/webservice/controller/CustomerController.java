package com.ecw.webservice.controller;

import com.ecw.webservice.dto.CustomerDTO;
import com.ecw.webservice.dto.CustomerDTOList;
import com.ecw.webservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {
    public static final String BASE_URL = "api/v1/customers";

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(BASE_URL)
    CustomerDTOList findAllCustomer(){
        CustomerDTOList customerDTOList = new CustomerDTOList();
        customerDTOList.setCustomers(customerService.findAllCustomer());
        return customerDTOList;
    }

    @GetMapping(BASE_URL+"/{id}")
    CustomerDTO findCustomerById(@PathVariable Long id){
        return customerService.findById(id);
    }

    @PostMapping(BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createCustomer(customerDTO);
    }

    @PatchMapping(BASE_URL+"/{id}")
    CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(id,customerDTO);
    }

    @DeleteMapping(BASE_URL+"/{id}")
    void deleteCustomer(@PathVariable Long id){
        customerService.deleteById(id);
    }

}
