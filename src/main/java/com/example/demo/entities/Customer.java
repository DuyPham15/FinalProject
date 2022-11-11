package com.example.demo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Vui lòng nhập Tên")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NotBlank(message = "Vui lòng nhập Họ")
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotBlank(message = "Vui lòng nhập Địa Chỉ")
	@Column(name = "address", nullable = false)
	private String address;
	
	@NotBlank(message = "Vui lòng nhập Phường, Xã")
	@Column(name = "ward", nullable = false)
	private String ward;
	
	@NotBlank(message = "Vui lòng nhập Quận, Huyện")
	@Column(name = "district", nullable = false)
	private String district;
	
	@NotBlank(message = "Vui lòng nhập Thành Phố")
	@Column(name = "city", nullable = false)
	private String city;
	
	@Email(message = "Invalid email")
	@Column(name = "email")
	private String email;
	
	@NotBlank(message = "Vui lòng nhập Số Điện Thoại")
	@Column(name = "PHONE_NUMBER", length = 20)
	private String phoneNumber;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}
