//package com.example.demo.entities;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//
//@Entity
//@Table(name = "category")
//public class Category {
//
//	@Column(name = "category_id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//	
//	@NotBlank(message = "Name is mandatory")
//	@Column(name = "name", nullable = false)
//	private String name;
//	
//	@NotBlank(message = "Tittle is mandatory")
//	@Column(name = "title", nullable = false)
//	private String title;
//	
//	@Column(name = "description")
//	private String description;
//	
//	@OneToMany(mappedBy = "category")
//	private List<Food> foods;
//}
