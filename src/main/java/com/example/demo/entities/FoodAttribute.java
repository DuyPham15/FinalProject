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
//import javax.validation.constraints.NotBlank;
//
//@Entity
//@Table(name = "food_attribute")
//public class FoodAttribute {
//
//	@Column(name = "attribute_id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//	
//	@NotBlank(message = "Name is mandatory")
//	@Column(name = "name", nullable = false)
//	private String name;
//	
//	@NotBlank(message = "Value is mandatory")
//	@Column(name = "value", nullable = false)
//	private String value;
//	
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	private Food food;
//}
