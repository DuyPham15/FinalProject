package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import net.bytebuddy.utility.nullability.MaybeNull;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@PrimaryKeyJoinColumn
	private EmployeeDetail employeeDetail;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Department department;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Permission> permissions;

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public EmployeeDetail getEmployeeDetail() {
		return employeeDetail;
	}

	public void setEmployeeDetail(EmployeeDetail employeeDetail) {
		this.employeeDetail = employeeDetail;
	}
}

