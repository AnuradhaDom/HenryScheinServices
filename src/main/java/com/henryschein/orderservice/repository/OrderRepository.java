package com.henryschein.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henryschein.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findAll();

}
