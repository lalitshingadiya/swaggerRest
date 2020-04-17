package com.ecw.webservice.service;

import com.ecw.webservice.controller.CustomerController;
import com.ecw.webservice.dto.CustomerDTO;
import com.ecw.webservice.entity.Customer;
import com.ecw.webservice.mapper.CustomerMapper;
import com.ecw.webservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> findAllCustomer() {
        List<CustomerDTO> list = new ArrayList();
        customerRepository.findAll().forEach(e -> {
            CustomerDTO customerDTO = customerMapper.toCustomerDTO(e);
            customerDTO.setCustomerUrl(getCustomerUrl(e.getId()));
            list.add(customerDTO);
        });
        return list;
    }

    @Override
    public CustomerDTO findById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if(customerOptional.isPresent()){
            CustomerDTO customerDTO = customerMapper.toCustomerDTO(customerOptional.get());
            customerDTO.setCustomerUrl(getCustomerUrl(customerDTO.getId()));
            return customerDTO;
        }
        return CustomerDTO.builder().build();
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerDTO));
        CustomerDTO customerDTO1 = customerMapper.toCustomerDTO(customer);
        customerDTO1.setCustomerUrl(getCustomerUrl(customer.getId()));
        return customerDTO1;
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).get();
        if(!customerDTO.getFirstName().equals(customer.getFirstName())){
            customer.setFirstName(customerDTO.getFirstName());
        }
        if(!customerDTO.getLastName().equals(customer.getLastName())){
            customer.setLastName(customerDTO.getLastName());
        }
        CustomerDTO customerDTO1 = customerMapper.toCustomerDTO(customerRepository.save(customer));
        customerDTO1.setCustomerUrl(getCustomerUrl(customer.getId()));
        return customerDTO1;
    }

    @Override
    public CustomerDTO replaceCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).get();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        CustomerDTO customerDTO1 = customerMapper.toCustomerDTO(customerRepository.save(customer));
        customerDTO1.setCustomerUrl(getCustomerUrl(customer.getId()));
        return customerDTO1;
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {
        customerRepository.delete(customerMapper.toCustomer(customerDTO));
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }


    private String getCustomerUrl(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }
}
