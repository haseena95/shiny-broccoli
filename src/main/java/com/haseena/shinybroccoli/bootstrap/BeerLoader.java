package com.haseena.shinybroccoli.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.haseena.shinybroccoli.domain.Beer;
import com.haseena.shinybroccoli.repositories.BeerRepository;

@Component
public class BeerLoader implements CommandLineRunner {
	
	private final BeerRepository beerRepository;

	public BeerLoader(BeerRepository beerRepository) {
	
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		loadBeerObject();
		
	}

	private void loadBeerObject() {
		if(beerRepository.count() == 0) {
			
			beerRepository.save(Beer.builder().
					beerName("Mango Bobs")
					.beerStyle("IPA")
					.quantityToBrew(200)
					.minOnHand(12)
					.upc(3456666666L)
					.price(new BigDecimal("12.95"))
					.build());
			beerRepository.save(Beer.builder().
					beerName("Galaxy Cat")
					.beerStyle("Pale Ale")
					.quantityToBrew(200)
					.minOnHand(12)
					.upc(34566663444L)
					.price(new BigDecimal("11.95"))
					.build());
			
			//System.out.println("Loader count" +beerRepository.count());
		}
		System.out.println("Loader count" +beerRepository.count());
		
	}

}
