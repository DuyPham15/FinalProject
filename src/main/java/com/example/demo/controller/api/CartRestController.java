package com.example.demo.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Food;
import com.example.demo.model.CartInfo;
import com.example.demo.service.FoodService;
import com.example.demo.utils.Utils;

@RestController
@RequestMapping(value = "/api/cart")
public class CartRestController {

	@Autowired
	private FoodService foodService;

	@GetMapping("/update-quantity")
	public CartInfo updateProductQuantity(@RequestParam(name = "productId") Long id,
			@RequestParam(name = "quantity") Long quantity, HttpServletRequest request) {
		Food food = foodService.getFoodById(id);
		CartInfo cartInfo = Utils.getCartInSession(request);
		if (food.getQuantity() >= quantity) {
			cartInfo.updateProduct(id, quantity);
			cartInfo.setMessage(null);
		}else {
			cartInfo.setMessage("Vượt quá số lượng được phép đặt");
		}
		return cartInfo;
	}
}
