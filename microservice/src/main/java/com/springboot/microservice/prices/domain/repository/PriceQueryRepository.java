package com.springboot.microservice.prices.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.springboot.microservice.prices.infraestructure.outbound.database.entity.Prices;

public interface PriceQueryRepository {

	List<Prices> findByApplicationDate(
			LocalDateTime application, 
			int product_id, 
			short subsidiary_id);
	
}
