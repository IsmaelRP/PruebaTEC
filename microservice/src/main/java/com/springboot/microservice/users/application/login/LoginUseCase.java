package com.springboot.microservice.users.application.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservice.users.domain.exceptions.InvalidCredentialsException;
import com.springboot.microservice.users.domain.model.TokenQuery;
import com.springboot.microservice.users.domain.repository.TokenQueryRepository;

@Service
public class LoginUseCase {


	@Autowired
	private TokenQueryRepository tokenQueryRepository;

    public TokenQuery login(String user, String password){    	
        return tokenQueryRepository.login(user, password)
        		.orElseThrow(() -> new InvalidCredentialsException("Credentials not found"));
    }

}
