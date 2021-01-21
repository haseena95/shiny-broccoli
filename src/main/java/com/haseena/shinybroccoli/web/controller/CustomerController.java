package com.haseena.shinybroccoli.web.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.haseena.shinybroccoli.web.model.CustomerDto;
import com.haseena.shinybroccoli.web.service.CustomerService;




@RestController
@RequestMapping("/api/v1/customer")
@Validated
public class CustomerController {
	
	private  CustomerService customerService;
	
public CustomerController(CustomerService custService) {
		
		this.customerService = custService;
	}



	@GetMapping("/{custId}")
	public ResponseEntity<CustomerDto> getBeerById(@NotNull @PathVariable("custId") UUID custId){
		
		
		return new ResponseEntity<>(CustomerDto.builder().build(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity savNewBeer(@Valid @RequestBody CustomerDto custDto){
		
		
		
		CustomerDto saveBeerDto = customerService.saveNewCustomer(custDto);
		  
		  HttpHeaders headers = new HttpHeaders(); headers.add("Location",
		 "/api/v1/customer/"+custDto.getId().toString());
		  
		  return new ResponseEntity(headers,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{custId}")
	public ResponseEntity updateBeerById(@PathVariable("custId") UUID custId,@Valid @RequestBody CustomerDto custDto){
		
		customerService.updateCustomer(custId,custDto);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{custId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleterBeerById(@PathVariable("custId") UUID custId){
		
		customerService.deleteById(custId);
		
	}

}
