package com.haseena.shinybroccoli.web.service;

import java.util.UUID;


import com.haseena.shinybroccoli.web.model.CustomerDto;

public interface CustomerService {
	
	public CustomerDto getCustomerById(UUID custId);
	public CustomerDto saveNewCustomer(CustomerDto custDto);
	public void updateCustomer(UUID custId,CustomerDto custDto);
	public void deleteById(UUID custId);

}
