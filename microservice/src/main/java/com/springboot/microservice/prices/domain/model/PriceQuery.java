package com.springboot.microservice.prices.domain.model;

import java.time.LocalDateTime;

public record PriceQuery(
	    int productId,
	    short brandId,
	    float price,
	    LocalDateTime startDate,
	    LocalDateTime endDate
) {}
