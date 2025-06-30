package com.springboot.microservice.users.infraestructure.inbound.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservice.shared.ResponseObj;
import com.springboot.microservice.users.application.login.LoginUseCase;
import com.springboot.microservice.users.domain.model.UserQuery;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
	
	@Autowired
	private LoginUseCase loginUseCase;

	Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@SuppressWarnings("rawtypes")
	@PostMapping("login")
    public ResponseEntity<ResponseObj> login(@RequestBody UserQuery user){
		
		var tokenQuery = this.loginUseCase.login(user.user(), user.password());
		logger.info("User controller - login successful");
		return ResponseEntity.ok(new ResponseObj<>(tokenQuery, "Credentials OK"));
    }
    
}
