package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.User;
import com.example.demo.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public String showUserList(Model model) {
	model.addAttribute("users", userService.getUsers());
	return "users";
	}
	
	@GetMapping("/signup")
	public String showSignUpForm(User user) {
	return "add-user";
	}
	
	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result, Model model) {
	if (result.hasErrors()) {
	return "add-user";
	}
	//userService.updateUser(user);
	return "redirect:/users";
	}
}
