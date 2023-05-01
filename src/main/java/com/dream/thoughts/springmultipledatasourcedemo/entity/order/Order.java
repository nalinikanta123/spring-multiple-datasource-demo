package com.dream.thoughts.springmultipledatasourcedemo.entity.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "order_amount")
	private Integer orderAmount;

	@Column(name = "user_id")
	private Integer userId;
}
