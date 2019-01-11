package com.mynotes.spring.data.mongodb.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mynotes.spring.data.mongodb.model.Product;

public interface  ProductRepository extends MongoRepository<Product, String>{

}
