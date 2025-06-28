package com.springboot.microservice.prices;

import java.time.LocalDateTime;

import com.springboot.microservice.prices.domain.model.PriceQuery;

public class PriceQueryTestData {

    public static PriceQuery samplePriceQuery() {
        return new PriceQuery(
            35455,                                  // productId
            (short) 1,                              // brandId
            35.50f,                                  // price
            LocalDateTime.of(2026, 6, 14, 0, 0, 0), // startDate
            LocalDateTime.of(2026, 12, 31, 23, 59, 59) // endDate
        );
    }
}