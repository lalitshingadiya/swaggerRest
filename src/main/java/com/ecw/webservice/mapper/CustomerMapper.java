package com.ecw.webservice.mapper;

import com.ecw.webservice.dto.CustomerDTO;
import com.ecw.webservice.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toCustomerDTO(Customer customer);


    Customer toCustomer(CustomerDTO customerDTO);
}
