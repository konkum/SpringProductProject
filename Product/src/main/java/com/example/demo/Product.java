package com.example.demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Product {
	private String code;
	private String description;
	private double price;

	public Product(String code, String description, double price) {
		this.code = code;
		this.description = description;
		this.price = price;
	}
}
