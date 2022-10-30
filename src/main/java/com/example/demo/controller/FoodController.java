package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.entities.Category;
import com.example.demo.entities.Food;
import com.example.demo.entities.Attribute;
import com.example.demo.entities.User;
import com.example.demo.service.AttributeService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.FoodService;

@Controller
@RequestMapping(value = "/admin")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AttributeService attributeService;
	
	@GetMapping("/foods")
	public String showFoodList(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("menuSelected", "food");
		int pageSize = 12;
		Page<Food> pageFood = foodService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Food> foods = pageFood.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pageFood.getTotalPages());
		model.addAttribute("totalItems", pageFood.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("foods", foods);
		return "foods";
	}

	@GetMapping("/addFood")
	public String showAddFoodForm(Model model) {
		List<Category> categories = categoryService.getCategories();
		List<Attribute> attributes = attributeService.getAttributes();
		model.addAttribute("food", new Food());
		model.addAttribute("categories", categories);
		model.addAttribute("attributes", attributes);
		return "add-food";
	}

	@GetMapping("/editFood")
	public String showEditForm(@RequestParam(name = "foodId") Long id, Model model) {
		List<Category> categories = categoryService.getCategories();
		List<Attribute> attributes = attributeService.getAttributes();
		Food food = foodService.findFoodById(id);
		model.addAttribute("food", food);
		model.addAttribute("categories", categories);
		model.addAttribute("attributes", attributes);
		return "edit-food";
	}
	
	@GetMapping("/deleteFood")
	public String deleteFood(@RequestParam(name = "foodId") Long id) {
		foodService.deleteFood(id);
		return "redirect:/admin/foods";
	}
	
	@GetMapping("/viewFood")
	public String viewFood(@RequestParam(name = "foodId") Long id, Model model) {
		Food food = foodService.findFoodById(id);
		model.addAttribute("food", food);
		return "view-food";
	}
		
	@PostMapping("/addFood")
	public String addUser(@Valid Food food, BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			List<Category> categories = categoryService.getCategories();
			List<Attribute> attributes = attributeService.getAttributes();
			model.addAttribute("categories", categories);
			model.addAttribute("attributes", attributes);
			return "add-food";
		}		
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		System.out.println("uploadRootPath=" + uploadRootPath);	
		foodService.saveFood(food, uploadRootPath);

		return "redirect:/admin/foods";
	}

	@PostMapping("/updateFood")
	public String updateFood(@RequestParam(name = "foodId") Long id, @Valid Food food, BindingResult result,
			Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			food.setId(id);
			List<Category> categories = categoryService.getCategories();
			List<Attribute> attributes = attributeService.getAttributes();
			model.addAttribute("categories", categories);
			model.addAttribute("attributes", attributes);
			return "edit-food";
		}
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		System.out.println("uploadRootPath=" + uploadRootPath);	
		foodService.updateFood(food, id, uploadRootPath);
		return "redirect:/admin/foods";
	}
}
