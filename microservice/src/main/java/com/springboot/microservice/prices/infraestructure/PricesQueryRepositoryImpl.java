package com.springboot.microservice.prices.infraestructure;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.microservice.prices.domain.repository.PriceQueryRepository;
import com.springboot.microservice.prices.infraestructure.outbound.database.PricesH2Repository;
import com.springboot.microservice.prices.infraestructure.outbound.database.entity.Prices;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PricesQueryRepositoryImpl implements PriceQueryRepository {

	@Autowired
	private PricesH2Repository pricesH2Repository;

	@Override
    public List<Prices> findByApplicationDate(LocalDateTime application, int productId, short subsidiaryId) {
		return pricesH2Repository.findPricesByDate(productId, subsidiaryId, application);
    }
	
}
