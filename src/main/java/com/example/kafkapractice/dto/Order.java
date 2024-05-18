package com.example.kafkapractice.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Order {

	private Long id;
	private LocalDateTime orderTime;
	private String productName;
	private Boolean orderStatus;
	
	@Builder
	public Order(Long id, LocalDateTime orderTime, String productName, Boolean orderStatus) {
		this.id = id;
		this.orderTime = orderTime;
		this.productName = productName;
		this.orderStatus = orderStatus;
	}
	
}
