package com.example.demo.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

//	 create 
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

//	 getbyid 
	public Employee get(Integer id) {
		return employeeRepository.findById(id).get();
	}

//  getall
	public List<Employee> listAll() {
		return employeeRepository.findAll();
	}

// update
	public Employee update(Employee employee, Integer id) {
		Employee empDB = employeeRepository.findById(id).get();

		if (Objects.nonNull(employee.getFirstName()) && !"".equalsIgnoreCase(employee.getFirstName())) {
			empDB.setFirstName(employee.getFirstName());
		}

		if (Objects.nonNull(employee.getLastName()) && !"".equalsIgnoreCase(employee.getLastName())) {
			empDB.setLastName(employee.getLastName());
		}

		if (Objects.nonNull(employee.getDob()) && !"".equals(employee.getDob())) {
			empDB.setDob(employee.getDob());
		}

		return employeeRepository.save(empDB);
	}

//  Delete
	public void delete(Integer id) {
		employeeRepository.deleteById(id);
	}

}