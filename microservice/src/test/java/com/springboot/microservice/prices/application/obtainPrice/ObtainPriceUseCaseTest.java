package com.springboot.microservice.prices.application.obtainPrice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.microservice.prices.PriceQueryTestData;
import com.springboot.microservice.prices.domain.model.PriceQuery;
import com.springboot.microservice.prices.domain.repository.PriceQueryRepository;
import com.springboot.microservice.prices.infraestructure.outbound.database.entity.Prices;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ObtainPriceUseCaseTest {

    @Mock
    private PriceQueryRepository priceQueryRepository;

    @InjectMocks
    private ObtainPriceUseCase obtainPriceUseCase;

    @Test
    public void testFindPrice() {
        List<Prices> priceQueryList = PriceQueryTestData.samplePriceQueryList();
        PriceQuery priceQueryResult = new PriceQuery(
        		priceQueryList.get(1).getId().getProductId(),
        		priceQueryList.get(1).getId().getBrandId(),
        		priceQueryList.get(1).getPrice(),
        	    priceQueryList.get(1).getStartDate(),
        	    priceQueryList.get(1).getEndDate()
        );
        
        when(priceQueryRepository.findByApplicationDate(
                any(LocalDateTime.class),
                anyInt(),
                anyShort()
        )).thenReturn(priceQueryList);

        PriceQuery result = obtainPriceUseCase.findPriceByDate(
        		priceQueryList.get(0).getStartDate(),
        		priceQueryList.get(0).getId().getProductId(),
                priceQueryList.get(0).getId().getBrandId()
        );

        assertEquals(result, priceQueryResult);
    }
    
}