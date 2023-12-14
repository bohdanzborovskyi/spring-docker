package com.zbodya.controllers;

import com.zbodya.entities.Employee;
import com.zbodya.exceptions.ResourceNotFoundException;
import com.zbodya.repositories.EmployeeRepository;
import com.zbodya.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeControler {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(path = "/all")
	public List<Employee> getEmployees(){
		employeeService.createSomeEmployees();
		return employeeRepository.findAll();		
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("Employee with id = " + id + " was not found"));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/")
	public Employee createEmployee(@RequestBody Employee employee){
		return employeeRepository.save(employee);
	}

	@PutMapping("/")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) throws ResourceNotFoundException {
		long id = employee.getId();
		Employee updatedEmployee = employeeRepository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("Employee with id = " + id + " was not found"));
		updatedEmployee.setEmail(employee.getEmail());
		updatedEmployee.setFirstName(employee.getFirstName());
		updatedEmployee.setLastName(employee.getLastName());
		return ResponseEntity.ok(employeeRepository.save(updatedEmployee));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id")Long id) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException("Employee with id = " + id + " was not found"));
		employeeRepository.delete(employee);
		return ResponseEntity.ok(employee);
	}
}
