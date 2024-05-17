package com.example.kafkapractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kafkapractice.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
