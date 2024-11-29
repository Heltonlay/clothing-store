package com.daniel.clothing_store.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@GetMapping(params = { "name" })
	public ResponseEntity<List<Employee>> findProductByName(@RequestParam String name) {
		List<Employee> employee = service.findByName(name);
		return ResponseEntity.ok().body(employee);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
		Employee employee = service.findById(id);
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping
	public ResponseEntity<URI> insert(@RequestBody Employee employee) {
		Long id = service.insert(employee);
		URI path = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(path);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Employee employee) {
		service.findById(id);
		employee.setId(id);
		service.update(employee);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
