package com.haseena.shinybroccoli.web.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.haseena.shinybroccoli.web.model.CustomerDto;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID custId) {
		return
				CustomerDto.builder()
				 .name("sam")
				 .id(UUID.randomUUID())
				 .build();
	}

	@Override
	public CustomerDto saveNewCustomer(CustomerDto custDto) {
		 return CustomerDto.builder().name("Allen").id(UUID.randomUUID()).
				 	build();
	}

	@Override
	public void updateCustomer(UUID custId, CustomerDto custDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(UUID custId) {
		// TODO Auto-generated method stub

	}

}
