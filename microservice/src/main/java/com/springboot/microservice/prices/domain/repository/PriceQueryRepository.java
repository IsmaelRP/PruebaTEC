package com.springboot.microservice.prices.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.springboot.microservice.prices.domain.model.PriceQuery;

public interface PriceQueryRepository {

	Optional<PriceQuery> findByApplicationDate(
			LocalDateTime application, 
			int product_id, 
			short subsidiary_id);
	
}
