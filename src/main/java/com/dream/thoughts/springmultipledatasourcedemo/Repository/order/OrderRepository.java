package com.dream.thoughts.springmultipledatasourcedemo.Repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream.thoughts.springmultipledatasourcedemo.entity.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}