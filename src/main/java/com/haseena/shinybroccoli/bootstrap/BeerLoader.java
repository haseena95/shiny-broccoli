package com.haseena.shinybroccoli.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.haseena.shinybroccoli.domain.Beer;
import com.haseena.shinybroccoli.repositories.BeerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@ComponentScan("com.haseena.shinybroccoli")

public class BeerLoader implements CommandLineRunner {
	
	
	  private final BeerRepository beerRepository;
	  
	  private static final String BEER_1_UPC = "0631234200036"; 
	  private static final String BEER_2_UPC = "0631234200019";
	  private static final String BEER_3_UPC = "0083783375213";
	  
		/*
		 * public BeerLoader(BeerRepository beerRepository) {
		 * 
		 * this.beerRepository = beerRepository; }
		 */
	 
	@Override
	public void run(String... args) throws Exception {
		
		loadBeerObject();
		
	}

	private void loadBeerObject() {
		
		  if(beerRepository.count() == 0) {
		  
		  beerRepository.save(Beer.builder(). beerName("Mango Bobs") .beerStyle("IPA")
		  .quantityToBrew(200) .minOnHand(12) .upc(BEER_1_UPC) .price(new
		  BigDecimal("12.95")) .build()); 
		  
		  beerRepository.save(Beer.builder().
		  beerName("Galaxy Cat") .beerStyle("Pale Ale") .quantityToBrew(200)
		  .minOnHand(12) .upc(BEER_2_UPC) .price(new BigDecimal("11.95")) .build());
		  
		  beerRepository.save(Beer.builder().
				  beerName("Dondu Beer") .beerStyle("Sona Masuri") .quantityToBrew(200)
				  .minOnHand(12) .upc(BEER_3_UPC) .price(new BigDecimal("11.95")) .build());
		  
		  
		  
		  //System.out.println("Loader count" +beerRepository.count()); }
		  System.out.println("Loader count" +beerRepository.count());
		  }
		
	}

}
