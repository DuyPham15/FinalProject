package com.example.demo.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Food;
import com.example.demo.entities.Attribute;
import com.example.demo.entities.FoodImage;
import com.example.demo.repository.FoodImageRepository;
import com.example.demo.repository.FoodRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private FoodImageRepository foodImageRepository;
	
	public List<Food> getFoods() {
		return this.foodRepository.findAll();
	}

	public Food saveFood(Food food) {
		Food savedFood = foodRepository.save(food);
		return savedFood;
	}
	
	public Food saveFood(Food food, String uploadRootPath) {
		File file = storageService.store(food.getThumbnailImageFile(), uploadRootPath);
		
		if (file != null) {
		food.setThumbnailImageName(file.getName());
		}
		
		List<MultipartFile> files = food.getFoodImageFiles();
		List<File> resultUploads = storageService.storeMultiFiles(files, uploadRootPath);
		for(File imageFile: resultUploads) {
			if(imageFile != null) {
				FoodImage image = new FoodImage();
				image.setImageName(imageFile.getName());
				image.setFood(food);
				food.getImages().add(image);
			}
		}
		
		this.saveFood(food);
		return food;
	}

	public Food updateFood(Food food, Long id, String uploadRootPath) {
		Food currentFood = findFoodById(id);
		currentFood.setCode(food.getCode());
		currentFood.setName(food.getName());
		currentFood.setQuantity(food.getQuantity());
		currentFood.setPrice(food.getPrice());
		currentFood.setPriceSpecial(food.getPriceSpecial());
		currentFood.setPriceSpecialStart(food.getPriceSpecialStart());
		currentFood.setPriceSpecialEnd(food.getPriceSpecialEnd());
		currentFood.setCategory(food.getCategory());
		currentFood.setAttribute(food.getAttribute());
		currentFood.setDescription(food.getDescription());
		
		File file = storageService.store(food.getThumbnailImageFile(), uploadRootPath);
		if(file!=null) {
			food.setThumbnailImageName(file.getName());
			currentFood.setThumbnailImageName(food.getThumbnailImageName());
		}
		
		List<MultipartFile> files = food.getFoodImageFiles();
		if (files!=null) {
			foodImageRepository.deleteByFoodId(id);
		}

		List<File> resultUploads = storageService.storeMultiFiles(files, uploadRootPath);

		for(File imageFile: resultUploads) {
			if(imageFile != null) {				
				FoodImage image = new FoodImage();
				image.setImageName(imageFile.getName());
				image.setFood(currentFood);
				currentFood.getImages().add(image);			}
		}
		return foodRepository.save(currentFood);
	}	

	public void deleteFood(Long id) {
		foodRepository.deleteById(id);
	}
	
	public Food findFoodById(Long id) {
		return this.foodRepository.findByFoodId(id);
	}
	
	public Food getFoodById(Long id) {
		return this.foodRepository.findByFoodId(id);
	}
	
	public Page<Food> getFoodByCategoryId(int pageNo, int pageSize, String sortField, String sortDirection, Long id){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Food> pageFood = foodRepository.findByCategoryId(pageable, id);
		return pageFood;
	}		

	public Page<Food> getFoodByKeyword(int pageNo, int pageSize, String sortField, String sortDirection, String keyword){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Food> pageFood = foodRepository.findByKeyword(pageable, keyword);
		return pageFood;
	}

	public Page<Food> findAll(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Food> pageFood = foodRepository.findAll(pageable);
		return pageFood;
	}
	
	
}
