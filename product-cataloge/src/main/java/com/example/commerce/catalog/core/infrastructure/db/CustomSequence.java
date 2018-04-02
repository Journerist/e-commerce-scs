package com.example.commerce.catalog.core.infrastructure.db;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class CustomSequence {
	@Id
	private String id;
	private long seq;
}
