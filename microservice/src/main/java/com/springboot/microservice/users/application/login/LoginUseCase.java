package com.springboot.microservice.users.application.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservice.users.domain.model.TokenQuery;
import com.springboot.microservice.users.domain.repository.TokenQueryRepository;

@Service
public class LoginUseCase {


	@Autowired
	private TokenQueryRepository tokenQueryRepository;

    public Optional<TokenQuery> login(String user, String password){
        return tokenQueryRepository.login(user, password);
    }

}
