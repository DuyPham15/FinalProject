package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Code is mandatory")
	@Column(name = "code", nullable = false)
	private long code;

	@Column(name = "description")
	private String description;

	@NotBlank(message = "Quantity is mandatory")
	@Column(name = "quantity", nullable = false)
	private long quantity;

	@NotBlank(message = "Price is mandatory")
	@Column(name = "price", nullable = false)
	private long price;

	@Column(name = "price_special")
	private long priceSpecial;

	@Column(name = "price_special_start")
	private Date priceSpecialStart;

	@Column(name = "price_special_end")
	private Date priceSpecialEnd;

	@Column(name = "IS_AVAILABLE", length = 1)
	private Boolean isAvailable=false;

	@Column(name = "IS_FREE_FREESHIPPING", length = 1)
	private Boolean isFreeShipping=false;
}
