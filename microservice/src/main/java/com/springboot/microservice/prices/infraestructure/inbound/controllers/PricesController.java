package com.springboot.microservice.prices.infraestructure.inbound.controllers;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservice.prices.application.obtainPrice.ObtainPriceUseCase;
import com.springboot.microservice.shared.ResponseObj;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PricesController {
	
	@Autowired
	private ObtainPriceUseCase pricesUseCase;

	Logger logger = LoggerFactory.getLogger(PricesController.class);
	
	@SuppressWarnings("rawtypes")
	@GetMapping("findPrice")
    public ResponseEntity<ResponseObj> findPrice(@RequestParam short subsidiary_id,
    											@RequestParam int product_id, 
    											@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime application){

		var priceQuery = pricesUseCase.findPriceByDate(application, product_id, subsidiary_id);
	    logger.info("Price controller - findPrice successful");
	    return ResponseEntity.ok(new ResponseObj<>(priceQuery, "Price found"));
        
    }
    
}
