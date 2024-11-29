package com.daniel.clothing_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daniel.clothing_store.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query(value = "SELECT * FROM employee WHERE LOWER(name) ~ LOWER(?1)", nativeQuery = true)
	public List<Employee> findByName(String name);
}
