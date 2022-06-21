package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.Product;
import com.example.demo.model.productRepo;

@RestController
@RequestMapping(path = "/",produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class RestApi {
	private productRepo proRepo;
	@Autowired
	public RestApi(productRepo proRepo) {
		this.proRepo = proRepo;
	}
	@GetMapping("/product")
	public Iterable<Product> getListProduct() {
		return proRepo.findAll();
	}
	@GetMapping("/product/edit/{id}")
	public ResponseEntity<Product> editPro(@PathVariable("id") int id){
		Optional<Product> pro = proRepo.findById(id);
		if(pro.isPresent()) {
			return new ResponseEntity<>(pro.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	@PostMapping("/product/edit/{id}")
	public Product editProPost(@PathVariable("id") int id,@RequestBody Product prod){
		Product pro = proRepo.findById(id).get();
		pro.setPrice(prod.getPrice());
		pro.setContent(prod.getContent());
		return proRepo.save(pro);
	}
}
