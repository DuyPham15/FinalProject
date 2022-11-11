package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Food;
import com.example.demo.entities.Order;
import com.example.demo.entities.Permission;
import com.example.demo.entities.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.FoodService;
import com.example.demo.service.OrderService;

@Controller
@RequestMapping(value = "/admin")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public String showOrderList(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", "order");
		int pageSize = 8;
		Page<Order> pageOrder = orderService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Order> orders = pageOrder.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageOrder.getTotalPages());
		model.addAttribute("totalItems", pageOrder.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("orders", orders);
		return "orders";
	}
	
	@GetMapping("/viewOrder")
	public String showOrderInfo(@RequestParam(name = "orderId") Long id, Model model) {
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "view-order";
	}
	
	@GetMapping("/packedOrder")
	public String packedOrder(@RequestParam(name = "orderId") Long id, Model model) {
		orderService.packedOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "view-order";
	}
	
	@GetMapping("/deliveriedOrder")
	public String deliveriedOrder(@RequestParam(name = "orderId") Long id, Model model) {
		orderService.deliveriedOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "view-order";
	}
	
	@GetMapping("/canceledOrder")
	public String canceledOrder(@RequestParam(name = "orderId") Long id, Model model) {
		orderService.canceledOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "view-order";
	}
	
	@GetMapping("/receivedOrder")
	public String receivedOrder(@RequestParam(name = "orderId") Long id, Model model) {
		orderService.receivedOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "view-order";
	}
	
	@GetMapping("/returnedOrder")
	public String returnedOrder(@RequestParam(name = "orderId") Long id, Model model) {
		orderService.returnedOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "view-order";
	}
	
	@GetMapping("/finishedOrder")
	public String finishedOrder(@RequestParam(name = "orderId") Long id, Model model) {
		orderService.finishedOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "view-order";
	}
	
	
}
