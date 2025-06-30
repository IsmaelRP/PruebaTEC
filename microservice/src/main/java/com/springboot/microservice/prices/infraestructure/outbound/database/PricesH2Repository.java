package com.springboot.microservice.prices.infraestructure.outbound.database;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.microservice.prices.infraestructure.outbound.database.entity.Prices;
import com.springboot.microservice.prices.infraestructure.outbound.database.entity.PricesId;


@Repository
public interface PricesH2Repository extends JpaRepository<Prices, PricesId> {

	@Query("SELECT p FROM Prices p WHERE p.id.productId = :productId AND p.id.brandId = :subsidiaryId AND p.startDate <= :dateTime AND p.endDate >= :dateTime")
	List<Prices> findPricesByDate(
	        @Param("productId") int productId, 
	        @Param("subsidiaryId") int subsidiaryId, 
	        @Param("dateTime") LocalDateTime dateTime);
	
}
