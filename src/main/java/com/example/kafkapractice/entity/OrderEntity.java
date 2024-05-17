package com.example.kafkapractice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "order_gen", sequenceName = "order_seq", initialValue = 1, allocationSize = 50)
public class OrderEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private LocalDateTime orderTime;
	
	private String produceName;
	
	private Boolean orderStatus;
	
	@Builder
	public OrderEntity(Long id, LocalDateTime orderTime, String produceName, Boolean orderStatus) {
		this.id = id;
		this.orderTime = orderTime;
		this.produceName = produceName;
		this.orderStatus = orderStatus;
	}
	
}
