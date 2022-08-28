package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "add-user";
	}

	@GetMapping("/edituser")
	public String showEditForm(@RequestParam(name = "userId") Long id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		return "edit-user";
	}
	
	@GetMapping("/deleteuser")
	public String deleteUser(@RequestParam(name = "userId") Long id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}

	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-user";
		}
		userService.saveUser(user);

		return "redirect:/users";
	}

	@PostMapping("/updateuser")
	public String updateUser(@RequestParam(name = "userId") Long id, @Valid User user, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "edit-user";
		}
		userService.updateUser(user, id);

		return "redirect:/users";
	}
}
