package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController

public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

//	 create api
	@PostMapping("/employee")
	public ResponseEntity<Employee> add(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.save(employee), HttpStatus.CREATED);
	}

// getbyid api
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> get(@PathVariable Integer id) {
		try {
			Employee employee = employeeService.get(id);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}

//	 getall api
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return employeeService.listAll();
	}

//	 update api
	@PutMapping("/employee/{id}")
	public Employee update(@RequestBody Employee employee, @PathVariable("id") Integer id) {
		return employeeService.update(employee, id);
	}

//	 delete api
	@DeleteMapping("/employee/{id}")
	public String deleteById(@PathVariable("id") Integer id) {
		employeeService.delete(id);
		return "Deleted Successfully";
	}

}
