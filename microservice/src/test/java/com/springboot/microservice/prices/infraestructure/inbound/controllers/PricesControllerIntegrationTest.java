package com.springboot.microservice.prices.infraestructure.inbound.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerIntegrationTest {

	@Autowired
    private MockMvc mockMvc;

	private String bearerToken;
	private String currentTestUser = "test";
	private String currentPassUser = "pass";
	
	@BeforeEach
    void authenticate() throws Exception {
        MvcResult result = mockMvc.perform(post("/users/login")
        		.contentType(MediaType.APPLICATION_JSON)
                .content("{ \"user\": \"" + currentTestUser + "\", \"password\": \"" + currentPassUser + "\" }"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        bearerToken = jsonNode.get("body").get("token").asText();
    }
	
    @Test
    void testPrueba1() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
        		.header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-06-14T10:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.body.productId").value(35455))
		        .andExpect(jsonPath("$.body.brandId").value(1))
		        .andExpect(jsonPath("$.body.price").value(35.5))
		        .andExpect(jsonPath("$.body.startDate").value("2020-06-14T00:00:00"))
		        .andExpect(jsonPath("$.body.endDate").value("2020-12-31T23:59:59"))
		        .andExpect(jsonPath("$.msg").value("Price found"));
    }
 
    @Test
    void testPrueba2() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
        		.header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-06-14T16:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.body.productId").value(35455))
		        .andExpect(jsonPath("$.body.brandId").value(1))
		        .andExpect(jsonPath("$.body.price").value(25.45))
		        .andExpect(jsonPath("$.body.startDate").value("2020-06-14T15:00:00"))
		        .andExpect(jsonPath("$.body.endDate").value("2020-06-14T18:30:00"))
		        .andExpect(jsonPath("$.msg").value("Price found"));
    }
    
    
    @Test
    void testPrueba3() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
        		.header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-06-15T10:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.body.productId").value(35455))
		        .andExpect(jsonPath("$.body.brandId").value(1))
		        .andExpect(jsonPath("$.body.price").value(30.5))
		        .andExpect(jsonPath("$.body.startDate").value("2020-06-15T00:00:00"))
		        .andExpect(jsonPath("$.body.endDate").value("2020-06-15T11:00:00"))
		        .andExpect(jsonPath("$.msg").value("Price found"));
    }
    
    @Test
    void testPrueba4() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
        		.header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-06-16T21:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.body.productId").value(35455))
		        .andExpect(jsonPath("$.body.brandId").value(1))
		        .andExpect(jsonPath("$.body.price").value(38.95))
		        .andExpect(jsonPath("$.body.startDate").value("2020-06-15T16:00:00"))
		        .andExpect(jsonPath("$.body.endDate").value("2020-12-31T23:59:59"))
		        .andExpect(jsonPath("$.msg").value("Price found"));
    }
    
    @Test
    void testPriceNotFound_noMatchingProductOrBrand() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
                .header("Authorization", bearerToken)
                .param("product_id", "99999") // Producto inexistente
                .param("application", "2020-06-14T10:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("Price not found"));
    }

    @Test
    void testPriceNotFound_applicationDateOutOfRange() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
                .header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-01-01T00:00:00") // Fecha antes de cualquier precio válido
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("Price not found"));
    }

    @Test
    void testPriceNotFound_invalidBrandId() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
                .header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-06-14T10:00:00")
                .param("subsidiary_id", "99") // brandId inexistente
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("Price not found"));
    }

    @Test
    void testInvalidProductIdType_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
                .header("Authorization", bearerToken)
                .param("product_id", "abc") // valor no numérico
                .param("application", "2020-06-14T10:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.body").value("Error, invalid parameter type"))
                .andExpect(jsonPath("$.msg").exists());
    }
    
    @Test
    void testMissingParameter_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
                .header("Authorization", bearerToken)
                // Falta product_id
                .param("application", "2020-06-14T10:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.body").value("Error, missing parameter"))
                .andExpect(jsonPath("$.msg").exists());
    }

    
    @Test
    void testInvalidDateFormat_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
                .header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "not-a-date") // formato no válido
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.body").value("Error, invalid parameter type"))
                .andExpect(jsonPath("$.msg").exists());
    }

    
    @Test
    void testUnauthorizedRequest_shouldReturn401() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
                // Sin Authorization
                .param("product_id", "35455")
                .param("application", "2020-06-14T10:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    
    @Test
    void testInvalidToken_shouldReturnUnauthorizedOrForbidden() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
                .header("Authorization", "Bearer INVALID_TOKEN")
                .param("product_id", "35455")
                .param("application", "2020-06-14T10:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }


}
