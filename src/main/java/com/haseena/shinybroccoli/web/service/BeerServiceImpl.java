package com.haseena.shinybroccoli.web.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.haseena.shinybroccoli.web.model.BeerDto;

@Service
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerById(UUID beerId) {
		return BeerDto.builder().beerName("Desi Daru").id(UUID.randomUUID()).beerStyle("Sona").build();

}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		// TODO Auto-generated method stub
		 return BeerDto.builder().beerName("Thanda").id(UUID.randomUUID()).beerStyle("lite").build();
	}
}
