package com.springboot.microservice.users.infraestructure.outbound.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")

public class Users {
	@Id
	private String usr;	
	
	@Column(name = "password")
	private String password;

}
