package com.haseena.shinybroccoli.web.mapper;

import org.mapstruct.Mapper;

import com.haseena.shinybroccoli.domain.Beer;
import com.haseena.shinybroccoli.web.model.BeerDto;

@Mapper(uses= {DateMapper.class})
public interface BeerMapper {
	BeerDto beerToBeerDto(Beer beer);
	Beer beerDtoToBeer(BeerDto beerDto);

}
