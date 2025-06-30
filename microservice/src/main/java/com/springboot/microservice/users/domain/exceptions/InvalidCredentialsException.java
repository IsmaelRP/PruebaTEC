package com.springboot.microservice.users.domain.exceptions;

public class InvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 5202879084126305284L;

	public InvalidCredentialsException(String message) {
        super(message);
    }
}