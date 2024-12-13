package com.daniel.clothing_store.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import com.daniel.clothing_store.exceptions.NotFoundException;
import com.daniel.clothing_store.repositories.ClothingRepository;
import com.daniel.clothing_store.services.ClothingService;

public class ClothingServiceTests {

	@Mock
	ClothingRepository clothingRepository;

	@Autowired
	@InjectMocks
	ClothingService clothingService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void AssertFindAll() {
		List<Clothing> c = List.of(
				new Clothing(1L, "nice shirts for summer", 30.0, 15, List.of(new Category(1L, "masculine", 0.1))),
				new Clothing(2L, "nice shirts for winter", 35.0, 12, List.of(new Category(2L, "feminine", 0.1))));
		when(clothingRepository.findAll()).thenReturn(c);
		List<Clothing> expected = c;
		List<Clothing> received = clothingService.findAll();
		verify(clothingRepository, times(1)).findAll();
		assertEquals(expected, received);
	}

	@Test
	public void AssertFindByName() {
		List<Clothing> c = List
				.of(new Clothing(1L, "nice shirts for summer", 30.0, 15, List.of(new Category(1L, "masculine", 0.1))));
		when(clothingRepository.findByName("sum")).thenReturn(c);
		List<Clothing> expected = c;
		List<Clothing> received = clothingService.findByName("sum");
		verify(clothingRepository, times(1)).findByName(any());
		assertEquals(expected, received);
	}

	@Test
	public void AssertFindByNameAndCategories() {
		List<Clothing> c = List
				.of(new Clothing(1L, "nice shirts for summer", 30.0, 15, List.of(new Category(1L, "masculine", 0.1))));
		when(clothingRepository.findByNameAndCategories("sum", "masculine", null, null, null, null)).thenReturn(c);
		List<Clothing> expected = c;
		List<Clothing> received = clothingService.findByNameAndCategories("sum", "masculine");
		verify(clothingRepository, times(1)).findByNameAndCategories(any(), any(), any(), any(), any(), any());
		assertEquals(expected, received);
	}

	@Test
	public void AssertFindById() {
		Optional<Clothing> c = Optional
				.of(new Clothing(1L, "nice shirts for summer", 30.0, 15, List.of(new Category(1L, "masculine", 0.1))));
		when(clothingRepository.findById(1L)).thenReturn(c);
		Clothing expected = c.get();
		Clothing received = clothingService.findById(1L);
		verify(clothingRepository, times(1)).findById(1L);
		assertEquals(expected, received);
	}

	@Test
	public void AssertInsert() {
		Clothing c = new Clothing(1L, "nice shirts for summer", 30.0, 15, List.of(new Category(1L, "masculine", 0.1)));
		when(clothingRepository.save(c)).thenReturn(c);
		Long expected = c.getId();
		Long received = clothingService.insert(c);
		verify(clothingRepository, times(1)).save(c);
		assertEquals(expected, received);
	}

	@Test
	public void AssertUpdate() {
		List<Category> categories = new ArrayList<>();
		categories.add(new Category(1L, "masculine", 0.1));
		Optional<Clothing> oldC = Optional.of(new Clothing(1L, "nice shirts for summer", 30.0, 15, categories));
		categories.clear();
		categories.add(new Category(1L, "masculine", 0.12));
		Clothing newC = new Clothing(1L, "nice shirts for summer", 35.0, 10, categories);

		when(clothingRepository.findById(1L)).thenReturn(oldC);
		when(clothingRepository.save(oldC.get())).thenReturn(oldC.get());
		clothingService.update(newC);
		Clothing expected = newC;
		Clothing received = clothingService.findById(1L);
		verify(clothingRepository, times(2)).findById(1L);
		verify(clothingRepository, times(1)).save(oldC.get());
		assertEquals(expected, received);
	}

	@Test
	public void AssertDelete() {
		Optional<Clothing> c = Optional
				.of(new Clothing(1L, "nice shirts for summer", 30.0, 15, List.of(new Category(1L, "masculine", 0.1))));
		when(clothingRepository.findById(1L)).thenReturn(c);
		clothingService.deleteById(1L);
		when(clothingRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> clothingService.findById(1L));
		verify(clothingRepository, times(2)).findById(1L);
		verify(clothingRepository, times(1)).deleteById(1L);
	}
}
