package com.springboot.microservice.prices.infraestructure.outbound.database.entity;

import java.time.LocalDateTime;

import com.springboot.microservice.shared.CurrEnum;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "prices")
public class Prices {

	@EmbeddedId
	private PricesId id;	
	
	@Column(name = "priority")
	private short priority;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "currency")
	private CurrEnum currency;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;
	
	@Column(name = "last_update_by")
	private String lastUpdateBy;

	
	
}
