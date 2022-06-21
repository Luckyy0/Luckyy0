package com.example.demo.test;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/")
public class userRest {
	@Autowired
	private RestTemplate rest;
	
	@GetMapping()
	public String home() {
		return "home";
	}
	@GetMapping("/user")
	public List<User> getUser()
	{
		List<User> users =
				Arrays.asList(rest.getForObject("http://localhost:8081/api/tacos/user",User[].class));
//		for(User i : users) {
//			System.out.print(i.getUsername());
//		}
		return users;
	}
	@GetMapping("/login")
	public String login(Model model)
	{
		model.addAttribute("user",new User());
		return "login";
	}
	@PostMapping("/login")
	public String redirect_login(User user, HttpSession session,Model model)
	{
		User aa = rest.getForObject("http://localhost:8081/api/tacos/user/finduser/{username}",User.class ,user.getUsername());
		
		session.setAttribute("user", aa);
		model.addAttribute("user",aa);
		return "edit";
	}
	@GetMapping("/singin")
	public String register(Model model)
	{
		model.addAttribute("user",new User());
		return "singin";
	}
	@PostMapping("/user/add")
	public String createIngredient(User user) {
		rest.postForObject("http://localhost:8081/api/tacos/user/add",user, User.class);
		return "home";
		}

	@PostMapping("/user/edit/{id}")
	public String edit(HttpSession session,User user) {
		User a = (User)session.getAttribute("user");
		
		System.out.print(user.getUsername());
		String ur = "http://localhost:8081/api/tacos/user/"+String.valueOf(a.getId());
		System.out.print(ur);
		rest.postForObject(ur,user,User.class);
		return "home";
	}
//	@DeleteMapping("/user/{id}")
}