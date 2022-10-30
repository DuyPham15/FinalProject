package com.example.demo.model;

import com.example.demo.entities.Food;

public class ProductInfo {

	private Long productId;
	private String productName;
	private Long productPrice;
	private Long productQuantity;
	private String productImage;
	
	public ProductInfo() {
		
	}
	
	public ProductInfo(Food food) {
		this.productId = food.getId();
		this.productName = food.getName();
		this.productPrice = food.getPrice();
		this.productQuantity = food.getQuantity();
		this.productImage = food.getThumbnailImageName();
	}
	
	
	public ProductInfo(Long productId, String productName, Long productPrice, Long productQuantity,
			String productImage) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.productImage = productImage;
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}

	
	
}
