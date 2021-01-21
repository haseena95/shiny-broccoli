package com.haseena.shinybroccoli.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haseena.shinybroccoli.web.model.BeerDto;
import com.haseena.shinybroccoli.web.service.BeerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
@ComponentScan("com.haseena.shinybroccoli")
public class BeerController {
	
	@Autowired
	private  BeerService beerService;
	@Autowired
	ObjectMapper objectMapper;
	


	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId){
		
		
		return new ResponseEntity<>(BeerDto.builder().build(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity savNewBeer(@Valid @RequestBody String beerDtoJson) throws JsonMappingException, JsonProcessingException{
		
		BeerDto beerDto = objectMapper.readValue(beerDtoJson,BeerDto.class);
		
		 BeerDto saveBeerDto = beerService.saveNewBeer(beerDto);
		  
		  HttpHeaders headers = new HttpHeaders(); headers.add("Location",
		 "/api/v1/beer/"+beerDto.getId());
		  
		  return new ResponseEntity(headers,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId,@Valid @RequestBody String beerDtoJson) throws JsonMappingException, JsonProcessingException{
		
		BeerDto beerDto = objectMapper.readValue(beerDtoJson,BeerDto.class);
		beerService.updateBeer(beerId,beerDto);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleterBeerById(@PathVariable("beerId") UUID beerId){
		
		beerService.deleteById(beerId);
		
	}
	
	

}
