package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.example.demo.entities.User;
import com.example.demo.entities.Attribute;
import com.example.demo.repository.AttributeRepository;
import com.example.demo.service.AttributeService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FoodService;
import com.example.demo.service.UserService;

@Controller
@Repository(value = "/")
public class IndexController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/home")
	public String homePage(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", "home");
		int pageSize = 8;
		Page<Food> pageFood = foodService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Food> foods = pageFood.getContent();
		List<Category> categories = categoryService.getCategories();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageFood.getTotalPages());
		model.addAttribute("totalItems", pageFood.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("categories", categories);
		model.addAttribute("foods", foods);
		return "index-shop";
	}
	
	@GetMapping("/about")
	public String aboutPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", "about");
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		return "about";
	}
	
	@GetMapping("/terms")
	public String termsPage(Model model) {
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		return "terms";
	}
	
	@GetMapping("/privacy")
	public String privacyPage(Model model) {
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		return "privacy";
	}
	
	@GetMapping("/shop/products")
	public String productsPage(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", "products");
		int pageSize=12;
		Page<Food> pageFood = foodService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Food> foods = pageFood.getContent();		
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageFood.getTotalPages());
		model.addAttribute("totalItems", pageFood.getTotalElements());
		model.addAttribute("foods", foods);
		model.addAttribute("categories", categories);

		return "products";
	}
	
	@GetMapping("/shop/viewProduct")
	public String showProductInfo(@RequestParam(name = "foodId") Long id, Model model) {
		List<Attribute> attributes = attributeService.getAttributes();
		List<Category> categories = categoryService.getCategories();
		Food food = foodService.findFoodById(id);
		model.addAttribute("food", food);
		model.addAttribute("categories", categories);
		model.addAttribute("attributes", attributes);
		return "view-product";
	}
	
	@GetMapping("/shop/viewCategory")
	public String showFoodByCategory(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model, @RequestParam(name = "categoryId") Long id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", "category");
		Category category = categoryService.findCategoryById(id);
		int pageSize=12;
		Page<Food> pageFood = foodService.getFoodByCategoryId(pageNo, pageSize, sortField, sortDir, id);
		List<Food> foods = pageFood.getContent();
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageFood.getTotalPages());
		model.addAttribute("totalItems", pageFood.getTotalElements());
		model.addAttribute("foods", foods);
		model.addAttribute("category", category);
		model.addAttribute("categories", categories);
		return "view-category";
	}
	
	@PostMapping("/shop/searchProduct")
	public String showFoodByKeyword(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model, @RequestParam(name = "keyword") String keyword,
			HttpServletRequest request)			 {
		HttpSession session = request.getSession();
		session.setAttribute("keywordSession", keyword);
		int pageSize=12;		
		Page<Food> pageFood = foodService.getFoodByKeyword(pageNo, pageSize, sortField, sortDir, keyword);
		List<Food> foods = pageFood.getContent();
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("keyword", keyword);
		model.addAttribute("totalPages", pageFood.getTotalPages());
		model.addAttribute("totalItems", pageFood.getTotalElements());
		model.addAttribute("foods", foods);
		model.addAttribute("categories", categories);
		return "search-product";
	}
	
	@GetMapping("/shop/searchProduct")
	public String getFoodByKeyword(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model, @RequestParam(name = "keyword") String keyword)			 {
		int pageSize=12;		
		Page<Food> pageFood = foodService.getFoodByKeyword(pageNo, pageSize, sortField, sortDir, keyword);
		List<Food> foods = pageFood.getContent();
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageFood.getTotalPages());
		model.addAttribute("totalItems", pageFood.getTotalElements());
		model.addAttribute("foods", foods);
		model.addAttribute("categories", categories);
		return "search-product";
	}
}
