package com.haseena.shinybroccoli.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.haseena.shinybroccoli.domain.Beer;

public interface BeerRepository  extends PagingAndSortingRepository<Beer,UUID>{

}
