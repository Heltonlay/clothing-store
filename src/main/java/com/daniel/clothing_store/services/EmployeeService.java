package com.daniel.clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.clothing_store.entities.Employee;
import com.daniel.clothing_store.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public List<Employee> findAll() {
		return repository.findAll();
	}

	public List<Employee> findByName(String name) {
		return repository.findByName(name);
	}

	public Employee findById(Long id) {
		Optional<Employee> employee = repository.findById(id);
		if (employee.isPresent())
			return employee.get();

		throw new RuntimeException();
	}

	public Long insert(Employee employee) {
		repository.save(employee);
		return employee.getId();
	}

	public void update(Employee updatedEmployee) {
		Employee employee = findById(updatedEmployee.getId());
		employee.setName(updatedEmployee.getName());
		employee.setAdmissionDate(updatedEmployee.getAdmissionDate());
		employee.setBaseSalary(updatedEmployee.getBaseSalary());
		repository.save(employee);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
