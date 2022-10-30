package com.example.demo.model;

public class CartLineInfo {

	private ProductInfo productInfo;
	private long quantity;
	private String message;
	private long amount;

//	public CartLineInfo() {
//		this.quantity = 0;
//	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getAmount() {
		return this.productInfo.getProductPrice() * this.quantity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
}
