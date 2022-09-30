package com.example.demo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "food")
public class Food {

	@Id
	@Column(name="food_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Code is mandatory")
	@Column(name = "food_code", nullable = false)
	private String foodCode;

	@Column(name = "food_description")
	private String description;

	@NotNull(message = "Quantity is mandatory")
	@Column(name = "food_quantity", nullable = false)
	private long quantity;

	@NotNull(message = "Price is mandatory")
	@Column(name = "food_price", nullable = false)
	private long price;

	@Column(name = "food_price_special")
	private long priceSpecial;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "food_price_special_start")
	private Date priceSpecialStart;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "food_price_special_end")
	private Date priceSpecialEnd;

	@Column(name = "IS_AVAILABLE", length = 1)
	private Boolean isAvailable=false;

	@Column(name = "IS_FREE_FREESHIPPING", length = 1)
	private Boolean isFreeShipping=false;
	
	@OneToMany(mappedBy = "food")
	private List<FoodImage> images;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;
	
	@OneToMany(mappedBy = "food")
	private List<FoodAttribute> attributes;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private OrderDetail orderDetail;
	
	

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return foodCode;
	}

	public void setCode(String code) {
		this.foodCode = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getPriceSpecial() {
		return priceSpecial;
	}

	public void setPriceSpecial(long priceSpecial) {
		this.priceSpecial = priceSpecial;
	}

	public Date getPriceSpecialStart() {
		return priceSpecialStart;
	}

	public void setPriceSpecialStart(Date priceSpecialStart) {
		this.priceSpecialStart = priceSpecialStart;
	}

	public Date getPriceSpecialEnd() {
		return priceSpecialEnd;
	}

	public void setPriceSpecialEnd(Date priceSpecialEnd) {
		this.priceSpecialEnd = priceSpecialEnd;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Boolean getIsFreeShipping() {
		return isFreeShipping;
	}

	public void setIsFreeShipping(Boolean isFreeShipping) {
		this.isFreeShipping = isFreeShipping;
	}

	public List<FoodImage> getImages() {
		return images;
	}

	public void setImages(List<FoodImage> images) {
		this.images = images;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<FoodAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<FoodAttribute> attributes) {
		this.attributes = attributes;
	}
	
	
}
