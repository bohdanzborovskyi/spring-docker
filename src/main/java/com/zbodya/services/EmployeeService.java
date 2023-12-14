package com.zbodya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zbodya.repositories.EmployeeRepository;

import com.zbodya.entities.Employee;

@Component
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public void createSomeEmployees() {
		Employee employee = Employee.builder()
				.firstName("Andrey")
				.lastName("Golubiev")
				.email("a.golubiev@gmail.com")
				.build();
		employeeRepository.save(employee);
		employee = Employee.builder()
				.firstName("Dono")
				.lastName("Gabbs")
				.email("d.gabbs@gmail.com")
				.build();
		employeeRepository.save(employee);
		employee = Employee.builder()
				.firstName("Adam")
				.lastName("Scott")
				.email("a.scott@gmail.com")
				.build();
		employeeRepository.save(employee);
	}

}
