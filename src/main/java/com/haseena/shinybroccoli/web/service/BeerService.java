package com.haseena.shinybroccoli.web.service;

import java.util.UUID;

import com.haseena.shinybroccoli.web.model.BeerDto;

public interface BeerService {
	public BeerDto getBeerById(UUID beerId);

	public BeerDto saveNewBeer(BeerDto beerDto);

}
