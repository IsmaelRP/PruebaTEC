package com.springboot.microservice.prices.application.obtainPrice;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservice.prices.domain.exceptions.PriceNotFoundException;
import com.springboot.microservice.prices.domain.model.PriceQuery;
import com.springboot.microservice.prices.domain.repository.PriceQueryRepository;


@Service
public class ObtainPriceUseCase {

	@Autowired
	private PriceQueryRepository priceQueryRepository;

    public PriceQuery findPriceByDate(LocalDateTime application, int product_id, short subsidiary_id){
    	return priceQueryRepository.findByApplicationDate(application, product_id, subsidiary_id)
    			.orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }

}
