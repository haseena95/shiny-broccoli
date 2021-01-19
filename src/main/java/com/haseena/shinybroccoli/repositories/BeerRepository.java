package com.haseena.shinybroccoli.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.haseena.shinybroccoli.domain.Beer;
@Repository
public interface BeerRepository  extends PagingAndSortingRepository<Beer,UUID>{

}
