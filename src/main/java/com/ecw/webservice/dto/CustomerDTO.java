package com.ecw.webservice.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    Long id;

    @NotBlank(message = "First Name can not be blank.")
    String firstName;

    @NotBlank(message = "Last Name can not be blank.")
    String lastName;

    @JsonSetter("customer_url")
    String customerUrl;
}
