package com.haseena.shinybroccoli.web.controller;


import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.haseena.shinybroccoli.web.model.BeerDto;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void getBeerById() throws Exception {
	
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer"+UUID.randomUUID().toString())
						.accept(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	void saveNewBeer() throws Exception {
		BeerDto beerDto = BeerDto.builder().build();
		String beerDtoJson= objectMapper.writeValueAsString(beerDto);
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(beerDtoJson))
						.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void updateBeerByID() throws Exception {
		BeerDto beerDto = BeerDto.builder().build();
		String beerDtoJson= objectMapper.writeValueAsString(beerDto);
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer"+ UUID.randomUUID().toString())
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(beerDtoJson))
						.andExpect(MockMvcResultMatchers.status().isCreated());
		
		
	}

}
