package com.daniel.clothing_store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.clothing_store.entities.Employee;
import com.daniel.clothing_store.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping
	public ResponseEntity<List<Employee>> findAllEmployees() {
		List<Employee> employees = service.findAll();
		return ResponseEntity.ok().body(employees);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
		Employee employee = service.findById(id);
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping
	public ResponseEntity<Long> insert(@RequestBody Employee employee) {
		Long id = service.insert(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Employee employee, @RequestParam Long id) {
		service.findById(id);
		service.update(employee);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteById(@RequestParam Long id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
