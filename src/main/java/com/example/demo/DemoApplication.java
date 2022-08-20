package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.EmployeeDetail;
import com.example.demo.entities.Permission;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private Customer customer;
	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CommandLineRunner");
		
//		Department dept = new Department();
//		dept.setName("IT");
//		
//		Permission adminPermission = new Permission();
//		adminPermission.setRole("ADMIN");
//		Permission managerPermission = new Permission();
//		managerPermission.setRole("MANAGER");
//		
//		List<Permission> permissions = new ArrayList<Permission>();
//		permissions.add(adminPermission);
//		permissions.add(managerPermission);
//		
//		Employee emp1 = new Employee();
//		emp1.setName("Hung");
//		emp1.setDepartment(dept);
//		emp1.setPermissions(permissions);
//		
//		employeeRepository.save(emp1);
		
//		Optional<Employee> employee5Optional = employeeRepository.findById(5);
//		Employee employee5 = employee5Optional.get();
//		employee5.setName("An");
//		employeeRepository.save(employee5);
//		employeeRepository.delete(employee5);
		}
	}

