package com.springboot.microservice.prices.application.obtainPrice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.microservice.prices.PriceQueryTestData;
import com.springboot.microservice.prices.domain.model.PriceQuery;
import com.springboot.microservice.prices.domain.repository.PriceQueryRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ObtainPriceUseCaseTest {

    @Mock
    private PriceQueryRepository priceQueryRepository;

    @InjectMocks
    private ObtainPriceUseCase obtainPriceUseCase;

    @Test
    public void testFindPrice() {
        PriceQuery priceQuery = PriceQueryTestData.samplePriceQuery();
        
        when(priceQueryRepository.findByApplicationDate(
        		priceQuery.startDate(),
				priceQuery.productId(), 
				priceQuery.brandId()
        	)).thenReturn(Optional.of(priceQuery));

        Optional<PriceQuery> result = obtainPriceUseCase.findPriceByDate(
        		priceQuery.startDate(),
        		priceQuery.productId(), 
        		priceQuery.brandId()
        );

        assertEquals(result.isPresent(), true);
        assertEquals(result.get(), priceQuery);
    }
}