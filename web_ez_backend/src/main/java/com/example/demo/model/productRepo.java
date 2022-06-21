package com.example.demo.model;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.data.Product;

public interface productRepo extends CrudRepository<Product, Integer> {

}
