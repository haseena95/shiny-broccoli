package com.haseena.shinybroccoli.web.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.haseena.shinybroccoli.web.model.BeerDto;
import com.haseena.shinybroccoli.web.model.BeerStyleEnum;

@Service
public class BeerServiceImpl implements BeerService {

	

	 @Override public BeerDto getBeerById(UUID beerId) {
		 return
				 BeerDto.builder()
				 .beerName("Desi Daru")
				 .id(UUID.randomUUID())
				 .beerStyle(BeerStyleEnum.AB).build();
	  
	  
	  }
	 
		
		 @Override public BeerDto saveNewBeer(BeerDto beerDto) { // TODO
		  
			 return BeerDto.builder().beerName("Thanda").id(UUID.randomUUID()).beerStyle(BeerStyleEnum.BB).
			 	build();
		 
		    
		 }


		@Override
		public void updateBeer(UUID beerId, BeerDto beerDto) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void deleteById(UUID beerId) {
			// TODO Auto-generated method stub
			
		}
		 
	 
	 
}
