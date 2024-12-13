package com.daniel.clothing_store.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.daniel.clothing_store.entities.Category;
import com.daniel.clothing_store.entities.Clothing;
import com.daniel.clothing_store.entities.Employee;
import com.daniel.clothing_store.entities.Sale;
import com.daniel.clothing_store.entities.enums.PaymentMethod;
import com.daniel.clothing_store.exceptions.NotFoundException;
import com.daniel.clothing_store.repositories.SaleRepository;
import com.daniel.clothing_store.services.SaleService;

public class SaleServiceTests {

	@Mock
	SaleRepository saleRepository;

	@Autowired
	@InjectMocks
	SaleService saleService;

	final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	Clothing clothing1 = new Clothing(1L, "nice shirts for summer", 30.0, 15,
			List.of(new Category(1L, "masculine", 0.1)));
	Clothing clothing2 = new Clothing(2L, "nice shirts for winter", 35.0, 12,
			List.of(new Category(2L, "feminine", 0.1)));

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void AssertFindAll() throws ParseException {
		Employee employee = new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0);
		List<Sale> s = List.of(
				new Sale(1L, PaymentMethod.DEBIT_CARD, "Joao", sdf.parse("05/04/2024"), employee, clothing1, clothing2),
				new Sale(2L, PaymentMethod.DEBIT_CARD, "Joana", sdf.parse("12/07/2024"), employee, clothing1));
		when(saleRepository.findAll()).thenReturn(s);
		List<Sale> expected = s;
		List<Sale> received = saleService.findAll();
		verify(saleRepository, times(1)).findAll();
		assertEquals(expected, received);
	}

	@Test
	public void AssertFindEmployeeById() throws ParseException {
		Employee employee = new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0);
		Optional<Sale> s = Optional.of(new Sale(1L, PaymentMethod.DEBIT_CARD, "Joao", sdf.parse("05/04/2024"), employee,
				clothing1, clothing2));
		when(saleRepository.findByEmployeeId(1L)).thenReturn(s);
		Sale expected = s.get();
		Sale received = saleService.findByEmployeeId(1L);
		verify(saleRepository, times(1)).findByEmployeeId(1L);
		assertEquals(expected, received);
	}

	@Test
	public void AssertFindById() throws ParseException {
		Employee employee = new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0);
		Optional<Sale> s = Optional.of(new Sale(1L, PaymentMethod.DEBIT_CARD, "Joao", sdf.parse("05/04/2024"), employee,
				clothing1, clothing2));
		when(saleRepository.findById(1L)).thenReturn(s);
		Sale expected = s.get();
		Sale received = saleService.findById(1L);
		verify(saleRepository, times(1)).findById(1L);
		assertEquals(expected, received);
	}

	@Test
	public void AssertInsert() throws ParseException {
		Employee employee = new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0);
		Sale s = new Sale(1L, PaymentMethod.DEBIT_CARD, "Joao", sdf.parse("05/04/2024"), employee, clothing1,
				clothing2);
		when(saleRepository.save(s)).thenReturn(s);
		Long expected = s.getId();
		Long received = saleService.insert(s);
		verify(saleRepository, times(1)).save(s);
		assertEquals(expected, received);
	}

	@Test
	public void AssertUpdate() throws ParseException {
		Employee employee = new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0);
		Optional<Sale> oldS = Optional.of(new Sale(1L, PaymentMethod.DEBIT_CARD, "Joao", sdf.parse("05/04/2024"),
				employee, clothing1, clothing2));
		Sale newS = new Sale(1L, PaymentMethod.DEBIT_CARD, "Joao", sdf.parse("02/04/2024"), employee, clothing1);
		when(saleRepository.findById(1L)).thenReturn(oldS);
		when(saleRepository.save(oldS.get())).thenReturn(oldS.get());
		saleService.update(newS);
		Sale expected = newS;
		Sale received = saleService.findById(1L);
		verify(saleRepository, times(2)).findById(1L);
		verify(saleRepository, times(1)).save(oldS.get());
		assertEquals(expected, received);
	}

	@Test
	public void AssertDelete() throws ParseException {
		Employee employee = new Employee(1L, "John", sdf.parse("10/03/2023"), 2000.0);
		Optional<Sale> s = Optional.of(new Sale(1L, PaymentMethod.DEBIT_CARD, "Joao", sdf.parse("05/04/2024"), employee,
				clothing1, clothing2));
		when(saleRepository.findById(1L)).thenReturn(s);
		saleService.deleteById(1L);
		when(saleRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> saleService.findById(1L));
		verify(saleRepository, times(2)).findById(1L);
		verify(saleRepository, times(1)).deleteById(1L);
	}
}
