package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entities.Order;
import com.example.demo.model.OrderSummary;
import com.example.demo.service.OrderService;

@Controller
public class ManageController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/admin/home")
	public String managePage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", "home");
		OrderSummary orderSummary = orderService.getOrderSummary();
		model.addAttribute(orderSummary);
		return "index-admin";
	}
}
