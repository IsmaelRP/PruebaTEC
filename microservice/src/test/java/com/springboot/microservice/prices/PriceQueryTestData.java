package com.springboot.microservice.prices;

import java.time.LocalDateTime;
import java.util.List;

import com.springboot.microservice.prices.domain.model.PriceQuery;
import com.springboot.microservice.prices.infraestructure.outbound.database.entity.Prices;
import com.springboot.microservice.prices.infraestructure.outbound.database.entity.PricesId;

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
    
	public static List<Prices> samplePriceQueryList() {
        Prices p1 = new Prices();
        PricesId id1 = new PricesId();
        id1.setProductId(35455);
        id1.setBrandId((short) 1);
        id1.setPriceList(1);
        p1.setId(id1);
        p1.setPriority((short) 1);
        p1.setPrice(35.50f);
        p1.setStartDate(LocalDateTime.of(2026, 6, 14, 0, 0));
        p1.setEndDate(LocalDateTime.of(2026, 12, 31, 23, 59, 59));

        Prices p2 = new Prices();
        PricesId id2 = new PricesId();
        id2.setProductId(35455);
        id2.setBrandId((short) 1);
        id2.setPriceList(2);
        p2.setId(id2);
        p2.setPriority((short) 3);   // mayor prioridad
        p2.setPrice(40.00f);
        p2.setStartDate(LocalDateTime.of(2026, 6, 14, 0, 0));
        p2.setEndDate(LocalDateTime.of(2026, 12, 31, 23, 59, 59));

        Prices p3 = new Prices();
        PricesId id3 = new PricesId();
        id3.setProductId(35455);
        id3.setBrandId((short) 1);
        id3.setPriceList(3);
        p3.setId(id3);
        p3.setPriority((short) 2);
        p3.setPrice(30.00f);
        p3.setStartDate(LocalDateTime.of(2026, 6, 14, 0, 0));
        p3.setEndDate(LocalDateTime.of(2026, 12, 31, 23, 59, 59));

        return List.of(p1, p2, p3);
    }
    
}