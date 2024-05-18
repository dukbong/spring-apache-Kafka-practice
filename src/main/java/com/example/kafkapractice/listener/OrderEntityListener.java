package com.example.kafkapractice.listener;

import org.springframework.stereotype.Component;

import com.example.kafkapractice.entity.OrderEntity;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/***
 * EntityListener를 통해 Entity의 변경을 감지한다.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEntityListener {

	@PostPersist
	@PostUpdate
	@PostRemove
	public void onchange(OrderEntity orderEntity) {
		log.info("변경 감지");
	}
	
}
