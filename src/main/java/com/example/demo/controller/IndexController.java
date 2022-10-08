package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Category;
import com.example.demo.entities.Food;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FoodService;

@Controller
@Repository(value = "/")
public class IndexController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("")
	public String homePage(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model) {
		int pageSize = 4;
		Page<Food> pageFood = foodService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Food> foods = pageFood.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageFood.getTotalPages());
		model.addAttribute("totalItems", pageFood.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("foods", foods);
		return "index";
	}
	
	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}
	
	@GetMapping("/terms")
	public String termsPage() {
		return "terms";
	}
	
	@GetMapping("/privacy")
	public String privacyPage() {
		return "privacy";
	}
	
	@GetMapping("/products")
	public String productsPage(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model) {
		int pageSize=12;
		Page<Food> pageFood = foodService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Food> foods = pageFood.getContent();
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("foods", foods);
		model.addAttribute("categories", categories);

		return "products";
	}
}
