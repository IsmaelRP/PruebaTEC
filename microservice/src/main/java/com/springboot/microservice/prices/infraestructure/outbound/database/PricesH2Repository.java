package com.springboot.microservice.prices.infraestructure.outbound.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.microservice.prices.infraestructure.outbound.database.entity.Prices;


@Repository
public interface PricesH2Repository extends JpaRepository<Prices, Long> {

}
