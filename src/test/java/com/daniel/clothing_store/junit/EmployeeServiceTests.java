package com.daniel.clothing_store.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.daniel.clothing_store.entities.Employee;
import com.daniel.clothing_store.exceptions.NotFoundException;
import com.daniel.clothing_store.repositories.EmployeeRepository;
import com.daniel.clothing_store.services.EmployeeService;

public class EmployeeServiceTests {

	@Mock
	EmployeeRepository employeeRepository;

	@Autowired
	@InjectMocks
	EmployeeService employeeService;

	final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void AssertFindAll() throws ParseException {
		List<Employee> e = List.of(new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0),
				new Employee(2L, "Joe", sdf.parse("02/06/2023"), 1500.0));
		when(employeeRepository.findAll()).thenReturn(e);
		List<Employee> expected = e;
		List<Employee> received = employeeService.findAll();
		verify(employeeRepository, times(1)).findAll();
		assertEquals(expected, received);
	}

	@Test
	public void AssertFindByName() throws ParseException {
		List<Employee> e = List.of(new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0));
		when(employeeRepository.findByName("john")).thenReturn(e);
		List<Employee> expected = e;
		List<Employee> received = employeeService.findByName("john");
		verify(employeeRepository, times(1)).findByName(any());
		assertEquals(expected, received);
	}

	@Test
	public void AssertFindById() throws ParseException {
		Optional<Employee> e = Optional.of(new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0));
		when(employeeRepository.findById(1L)).thenReturn(e);
		Employee expected = e.get();
		Employee received = employeeService.findById(1L);
		verify(employeeRepository, times(1)).findById(1L);
		assertEquals(expected, received);
	}

	@Test
	public void AssertInsert() throws ParseException {
		Employee e = new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0);
		when(employeeRepository.save(e)).thenReturn(e);
		Long expected = e.getId();
		Long received = employeeService.insert(e);
		verify(employeeRepository, times(1)).save(e);
		assertEquals(expected, received);
	}

	@Test
	public void AssertUpdate() throws ParseException {
		Optional<Employee> oldE = Optional.of(new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0));
		Employee newE = new Employee(1L, "John", sdf.parse("10/03/2023"), 2500.0);
		when(employeeRepository.findById(1L)).thenReturn(oldE);
		when(employeeRepository.save(oldE.get())).thenReturn(oldE.get());
		employeeService.update(newE);
		Employee expected = newE;
		Employee received = employeeService.findById(1L);
		verify(employeeRepository, times(2)).findById(1L);
		verify(employeeRepository, times(1)).save(oldE.get());
		assertEquals(expected, received);
	}

	@Test
	public void AssertDelete() throws ParseException {
		Optional<Employee> e = Optional.of(new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0));
		when(employeeRepository.findById(1L)).thenReturn(e);
		employeeService.deleteById(1L);
		when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> employeeService.findById(1L));
		verify(employeeRepository, times(2)).findById(1L);
		verify(employeeRepository, times(1)).deleteById(1L);
	}
}
