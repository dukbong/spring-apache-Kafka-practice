package com.example.kafkapractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kafkapractice.entity.OrderEntity;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {

}
