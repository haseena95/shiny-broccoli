package com.haseena.shinybroccoli.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haseena.shinybroccoli.web.model.BeerDto;
import com.haseena.shinybroccoli.web.service.BeerService;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	
	private final BeerService beerService;
	
	
	
	public BeerController(BeerService beerService) {
		
		this.beerService = beerService;
	}



	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){
		return null;
		
		//return new ResponseEntity<>(beerService.getBeerById(beerId),HttpStatus.OK);
	}
	
	
	public ResponseEntity handlePostRequest(BeerDto beerDto){
		return null;
		
		/*
		 * BeerDto saveBeerDto = beerService.saveNewBeer(beerDto);
		 * 
		 * HttpHeaders headers = new HttpHeaders(); headers.add("Location",
		 * "/api/v1/beer/"+saveBeerDto.getId().toString());
		 * 
		 * return new ResponseEntity(headers,HttpStatus.CREATED);
		 */
	}
	

}
