package com.springboot.microservice.users.domain.repository;

import java.util.Optional;

import com.springboot.microservice.users.domain.model.TokenQuery;

public interface TokenQueryRepository {
	
	Optional<TokenQuery> login(String user, String password);


}
