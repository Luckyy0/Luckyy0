package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.data.Product;

@Controller
@RequestMapping("/")
public class productController {
	@Autowired
	private RestTemplate rest;
	
	@GetMapping()
	public String home() {
		return "home";
	}
	
	@GetMapping("/product")
	public String viewPro(Model model){
		List<Product> pro = Arrays.asList(rest.getForObject("http://localhost:8081/product",Product[].class));
		model.addAttribute("pro",pro);
		return "viewProduct";
	}
	@GetMapping("/product/edit/{id}")
	public String editPro(Model model,@PathVariable("id") int id ){
		Product pro = rest.getForObject("http://localhost:8081/product/edit/{id}", Product.class,id);
		model.addAttribute("ePro",pro);
		return "edit";
	}
	@PostMapping("/product/edit/{id}")
	public String edit(Product pro) {
		System.out.print(pro.getId());
		System.out.print(pro.getPrice());
		System.out.print(pro.getContent());
		rest.postForObject("http://localhost:8081/product/edit/{id}",pro, Product.class,pro.getId());
		return "redirect:/product";
	}
}
