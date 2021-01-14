package com.haseena.shinybroccoli.web.mapper;

import org.mapstruct.Mapper;

import com.haseena.shinybroccoli.domain.Customer;
import com.haseena.shinybroccoli.web.model.CustomerDto;


@Mapper
public interface CustomerMapper {

	CustomerDto customerToCustomerDto(Customer cust);
	Customer customerDtoToCustomer(CustomerDto customerDto);
}

