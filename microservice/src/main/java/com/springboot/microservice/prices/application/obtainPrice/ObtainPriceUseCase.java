package com.springboot.microservice.prices.application.obtainPrice;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservice.prices.domain.model.PriceQuery;
import com.springboot.microservice.prices.domain.repository.PriceQueryRepository;


@Service
public class ObtainPriceUseCase {

	@Autowired
	private PriceQueryRepository priceQueryRepository;

    public Optional<PriceQuery> findPriceByDate(LocalDateTime application, int product_id, short subsidiary_id){
        return priceQueryRepository.findByApplicationDate(application, product_id, subsidiary_id);
    }

}
