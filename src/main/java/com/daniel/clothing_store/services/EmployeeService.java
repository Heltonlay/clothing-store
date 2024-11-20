package com.daniel.clothing_store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.clothing_store.entities.Employee;
import com.daniel.clothing_store.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	public List<Employee> getEmployees()
	{
		return repository.findAll();
	}
}
