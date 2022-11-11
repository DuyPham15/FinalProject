package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Category;
import com.example.demo.entities.Food;
import com.example.demo.model.CartInfo;
import com.example.demo.model.CustomerInfo;
import com.example.demo.model.ProductInfo;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FoodService;
import com.example.demo.service.OrderService;
import com.example.demo.utils.Utils;

@Controller
@RequestMapping(value = "/shop")
public class ShoppingCartController {

	@Autowired
	private FoodService foodService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/buyProduct")
	public String listProductHandler(@RequestParam(name = "foodId") Long id, 
			@RequestParam(name = "quantity") Long quantity, 
			Model model, HttpServletRequest request) {
		Food food = null;
		if (id != null) {
			food = foodService.findFoodById(id);
		}
		if (food != null) {
			CartInfo cartInfo = Utils.getCartInSession(request);
			ProductInfo productInfo = new ProductInfo(food);
			cartInfo.addProduct(productInfo, quantity, food.getQuantity());
		}
		return "redirect:/shop/shoppingCart";
	}

	@GetMapping("/shoppingCart")
	public String shoppingCartHandler(HttpServletRequest request, Model model) {
		CartInfo myCart = Utils.getCartInSession(request);
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", "cart");
		session.setAttribute("itemCount", myCart.getQuantityTotal());
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("cartForm", myCart);
		model.addAttribute("categories", categories);
		return "shop/cart/shoppingCart";
	}

	@GetMapping("/shoppingCartRemoveProduct")
	public String removeProductHandler(HttpServletRequest request, Model model,
			@RequestParam(name = "foodId") Long id) {
		Food food = null;
		if (id != null) {
			food = foodService.findFoodById(id);
		}
		if (food != null) {
			CartInfo cartInfo = Utils.getCartInSession(request);
			ProductInfo productInfo = new ProductInfo(food);
			cartInfo.removeProduct(productInfo);
		}
		return "redirect:/shop/shoppingCart";
	}

	@GetMapping("/shoppingCartCustomer")
	public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
		List<Category> categories = categoryService.getCategories();
		CartInfo cartInfo = Utils.getCartInSession(request);
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		if (customerInfo == null) {
			customerInfo = new CustomerInfo();
		}
		model.addAttribute("customerInfo", customerInfo);
		model.addAttribute("categories", categories);
		return "shop/cart/checkOut";
	}

	@GetMapping("/shoppingCartConfirmation")
	public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
		List<Category> categories = categoryService.getCategories();
		CartInfo cartInfo = Utils.getCartInSession(request);
		if (cartInfo == null || cartInfo.isEmpty()) {
			return "redirect:/shop/shoppingCart";
		}
		model.addAttribute("myCart", cartInfo);
		model.addAttribute("categories", categories);
		return "shop/cart/shoppingCartConfirmation";
	}

	@GetMapping("/shoppingCartFinalize")
	public String shoppingCartFinalize(HttpServletRequest request, Model model) {
		List<Category> categories = categoryService.getCategories();
		CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
		if (lastOrderedCart == null) {
			return "redirect:/shop/shoppingCart";
		}
		model.addAttribute("lastOrderedCart", lastOrderedCart);
		model.addAttribute("categories", categories);
		return "shop/cart/shoppingCartFinalize";
	}

	@PostMapping("/shoppingCart")
	public String shoppingCartUpdateQty(HttpServletRequest request, Model model,
			@ModelAttribute("cartForm") CartInfo cartForm) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		cartInfo.updateQuantity(cartForm);
		return "redirect:/shop/shoppingCart";
	}

	@PostMapping("/shoppingCartCustomer")
	public String shoppingCartCustomerSave(@ModelAttribute("customerInfo") CustomerInfo customerInfo,
			HttpServletRequest request, Model model) {
		// HttpSession session = request.getSession();
		CartInfo cartInfo = Utils.getCartInSession(request);
		cartInfo.setCustomerInfo(customerInfo);
		String error = orderService.addOrder(cartInfo);
		if (error != null) {
			// request.setAttribute("errorMessage", error);
			model.addAttribute("errorMessage", error);
			return "shop/cart/checkOut";
		} else {
			// request.setAttribute("errorMessage", null);
		}
		return "redirect:/shop/shoppingCartConfirmation";
	}

	@PostMapping("/shoppingCartConfirmation")
	public String shoppingCartConfirmationSave(HttpServletRequest request) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		if (cartInfo.isEmpty()) {
			return "redirect:/shop/shoppingCart";
		}
		// Remove Cart from Session.
		Utils.removeCartInSession(request);
		// Store last cart.
		Utils.storeLastOrderedCartInSession(request, cartInfo);
		return "redirect:/shop/shoppingCartFinalize";
	}
}
