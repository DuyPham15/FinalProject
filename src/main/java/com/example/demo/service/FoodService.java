package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Food;

import com.example.demo.repository.FoodRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodReposity;
	
	public List<Food> getFoods() {
		return this.foodReposity.findAll();
	}

	public Food saveFood(Food food) {
		Food savedFood = foodReposity.save(food);
		return savedFood;
	}

	public Food updateFood(Food food, Long id) {
		Food currentFood = findFoodById(id);
		currentFood.setCode(food.getCode());
		currentFood.setDescription(food.getDescription());
		currentFood.setQuantity(food.getQuantity());
		currentFood.setPrice(food.getPrice());
		currentFood.setPriceSpecial(food.getPriceSpecial());
		currentFood.setPriceSpecialStart(food.getPriceSpecialStart());
		currentFood.setPriceSpecialEnd(food.getPriceSpecialEnd());
		currentFood.setCategory(food.getCategory());
		return foodReposity.save(currentFood);
	}

	public void deleteFood(Long id) {
		foodReposity.deleteById(id);
	}

	public Food findFoodById(Long id) {
		Optional<Food> optionalFood = foodReposity.findById(id);
		return optionalFood.get();
	}

	public Food getByUserName(String foodCode) {
		return foodReposity.findByFoodCode(foodCode);

	}

	public Page<Food> findAll(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Food> pageFood = foodReposity.findAll(pageable);
		return pageFood;
	}
}
