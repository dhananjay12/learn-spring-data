package com.mynotes.spring.data.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mynotes.spring.data.mongodb.model.Product;
import com.mynotes.spring.data.mongodb.repo.ProductRepository;

@RestController
public class MyController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping()
	public List<Product> getAllUsers() {
		return productRepository.findAll();
	}
	
	@PostMapping(value = "/create")
	public Product addNewUsers(@RequestBody Product product) {		
		return productRepository.save(product);
	}

}
