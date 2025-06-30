package com.springboot.microservice.prices.infraestructure.inbound.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.microservice.prices.PriceQueryTestData;
import com.springboot.microservice.prices.application.obtainPrice.ObtainPriceUseCase;
import com.springboot.microservice.prices.domain.exceptions.PriceNotFoundException;
import com.springboot.microservice.prices.domain.model.PriceQuery;


@WebMvcTest(controllers = PricesController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class PricesControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockitoBean
    private ObtainPriceUseCase pricesUseCase;


    private final PriceQuery priceQuery = PriceQueryTestData.samplePriceQuery();
    
    @Test
    void findPrice_PriceFound_ReturnsOkResponse() throws Exception {
        when(pricesUseCase.findPriceByDate(
                priceQuery.startDate(),
                priceQuery.productId(),
                priceQuery.brandId()
        )).thenReturn(priceQuery);

        mockMvc.perform(get("/prices/findPrice")
                .param("subsidiary_id", String.valueOf(priceQuery.brandId()))
                .param("product_id", String.valueOf(priceQuery.productId()))
                .param("application", priceQuery.startDate().toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("Price found"))
            .andExpect(jsonPath("$.body.productId").value(priceQuery.productId()))
            .andExpect(jsonPath("$.body.brandId").value((int) priceQuery.brandId()))
            .andExpect(jsonPath("$.body.price").value((double) priceQuery.price()));
    }


    @Test
    void findPrice_PriceNotFound_ReturnsNotFoundResponse() throws Exception {
        int nonProductId = 9999;
        short brandId = 1;
        String application = "2020-06-14T10:00:00";

        when(pricesUseCase.findPriceByDate(
                LocalDateTime.parse(application),
                nonProductId,
                brandId
        )).thenThrow(new PriceNotFoundException("Price not found"));

        mockMvc.perform(get("/prices/findPrice")
                .param("subsidiary_id", String.valueOf(brandId))
                .param("product_id", String.valueOf(nonProductId))
                .param("application", application)
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.msg").value("Price not found"))
            .andExpect(jsonPath("$.body").value("Error"));
    }




}