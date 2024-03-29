package com.example.demo.test;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor()
@AllArgsConstructor
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
}
