package com.springboot.microservice.prices.domain.exceptions;

public class PriceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7470632411100393125L;

	public PriceNotFoundException(String message) {
        super(message);
    }
}