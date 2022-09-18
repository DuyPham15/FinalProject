//package com.example.demo.entities;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "foodimage")
//public class FoodImage {
//
//	@Column(name = "image_id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//	
//	@Column(name = "image")
//	private String image;
//	
//	@Column(name = "default_image")
//	private String defaultImage;
//	
//	@Column(name = "image_url")
//	private String imageURL;
//	
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	private Food food;
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getImage() {
//		return image;
//	}
//
//	public void setImage(String image) {
//		this.image = image;
//	}
//
//	public String getDefaultImage() {
//		return defaultImage;
//	}
//
//	public void setDefaultImage(String defaultImage) {
//		this.defaultImage = defaultImage;
//	}
//
//	public String getImageURL() {
//		return imageURL;
//	}
//
//	public void setImageURL(String imageURL) {
//		this.imageURL = imageURL;
//	}
//
//	public Food getFood() {
//		return food;
//	}
//
//	public void setFood(Food food) {
//		this.food = food;
//	}
//	
//	
//}
