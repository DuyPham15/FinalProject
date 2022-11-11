package com.example.demo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "permission")
public class Permission {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;
	
	@NotBlank(message = "Vui lòng nhập Tên Quyền")
	@Column(name = "role",nullable = false)
	private String role;
	
	@ManyToMany(mappedBy = "permissions")
	private List<User> users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	
	
}
