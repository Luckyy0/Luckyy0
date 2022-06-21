package com.example.demo.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String content;
	private double price;
}
