package com.springboot.microservice.prices.application.obtainPrice;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservice.prices.domain.exceptions.PriceNotFoundException;
import com.springboot.microservice.prices.domain.model.PriceQuery;
import com.springboot.microservice.prices.domain.repository.PriceQueryRepository;
import com.springboot.microservice.prices.infraestructure.outbound.database.entity.Prices;


@Service
public class ObtainPriceUseCase {

	@Autowired
	private PriceQueryRepository priceQueryRepository;

    public PriceQuery findPriceByDate(LocalDateTime application, int product_id, short subsidiary_id){
    	
    	List<Prices> prices = priceQueryRepository.findByApplicationDate(application, product_id, subsidiary_id);

    	return prices.stream()
                .max(Comparator.comparing(Prices::getPriority))
                .map(p -> new PriceQuery(
                        p.getId().getProductId(),
                        p.getId().getBrandId(),
                        p.getPrice(),
                        p.getStartDate(),
                        p.getEndDate()
                ))
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }

}
