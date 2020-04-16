package com.ecw.webservice.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    Long id;

    String firstName;

    String lastName;

    @JsonSetter("customer_url")
    String customerUrl;
}
