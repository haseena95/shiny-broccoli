package com.haseena.shinybroccoli.web.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.haseena.shinybroccoli.domain.Beer;
import com.haseena.shinybroccoli.repositories.BeerRepository;
import com.haseena.shinybroccoli.web.mapper.BeerMapper;
import com.haseena.shinybroccoli.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;
	

	 @Override public BeerDto getBeerById(UUID beerId)  {
		 try {
			return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundExeception::new)
					 );
		} catch (NotFoundExeception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
				 
		 	
	  
	  }
	 
		
		 @Override public BeerDto saveNewBeer(BeerDto beerDto) { // TODO
		  
			 return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
		 
		    
		 }


		@Override
		public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
			Beer beer = null;
			try {
				 beer = beerRepository.findById(beerId).orElseThrow(NotFoundExeception::new);
			} catch (NotFoundExeception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
					  
			beer.setBeerName(beerDto.getBeerName());
			beer.setBeerStyle(beerDto.getBeerStyle().name());
			beer.setPrice(beerDto.getPrice());
			beer.setUpc(beerDto.getUpc());
			return beerMapper.beerToBeerDto(beerRepository.save(beer));
			
		}


		@Override
		public void deleteById(UUID beerId) {
			beerRepository.deleteById(beerId);
			
		}


		
		 
	 
	 
}
