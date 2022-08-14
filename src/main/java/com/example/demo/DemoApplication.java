package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Employee;
import com.example.demo.entities.EmployeeDetail;
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
		System.out.println("Customer Name: " +customer.getPersonName());
		
		Employee employee1 = new Employee();
		employee1.setName("Nguyen Van A");
		EmployeeDetail employee1Dtl = new EmployeeDetail();
		employee1Dtl.setAge(20);
		
		employee1.setEmployeeDetail(employee1Dtl);
		Employee savedEmployee = employeeRepository.save(employee1);
		
		List<Employee> employeeList = employeeRepository.findAll();
		for(Employee employee : employeeList) {
			System.out.println("Employee Name: "+ employee.getName());
		}
	}

}
