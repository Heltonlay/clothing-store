package com.daniel.clothing_store.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.daniel.clothing_store.entities.Category;
import com.daniel.clothing_store.exceptions.NotFoundException;
import com.daniel.clothing_store.repositories.CategoryRepository;
import com.daniel.clothing_store.services.CategoryService;

public class CategoryServiceTests {

	@Mock
	CategoryRepository categoryRepository;

	@Autowired
	@InjectMocks
	CategoryService categoryService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void AssertFindAll() {
		List<Category> c = List.of(new Category(1L, "fashion", 0.2), new Category(2L, "sportive", 0.2));
		when(categoryRepository.findAll()).thenReturn(c);
		List<Category> expected = c;
		List<Category> received = categoryService.findAll();
		verify(categoryRepository, times(1)).findAll();
		assertEquals(expected, received);
	}

	@Test
	public void AssertFindByName() {
		List<Category> c = List.of(new Category(1L, "masculine", 0.2), new Category(2L, "feminine", 0.2));
		when(categoryRepository.findByName("ine")).thenReturn(c);
		List<Category> expected = c;
		List<Category> received = categoryService.findByName("ine");
		verify(categoryRepository, times(1)).findByName(any());
		assertEquals(expected, received);
	}

	@Test
	public void AssertFindById() {
		Optional<Category> c = Optional.of(new Category(1L, "fashion", 0.2));
		when(categoryRepository.findById(1L)).thenReturn(c);
		Category expected = c.get();
		Category received = categoryService.findById(1L);
		verify(categoryRepository, times(1)).findById(1L);
		assertEquals(expected, received);
	}

	@Test
	public void AssertInsert() {
		Category c = new Category(1L, "fashion", 0.2);
		when(categoryRepository.save(c)).thenReturn(c);
		Long expected = c.getId();
		Long received = categoryService.insert(c);
		verify(categoryRepository, times(1)).save(c);
		assertEquals(expected, received);
	}

	@Test
	public void AssertUpdate() {
		Optional<Category> oldC = Optional.of(new Category(1L, "fashion", 0.1));
		Category newC = new Category(1L, "fashion", 0.2);
		when(categoryRepository.findById(1L)).thenReturn(oldC);
		when(categoryRepository.save(oldC.get())).thenReturn(oldC.get());
		categoryService.update(newC);
		Category expected = newC;
		Category received = categoryService.findById(1L);
		verify(categoryRepository, times(2)).findById(1L);
		verify(categoryRepository, times(1)).save(oldC.get());
		assertEquals(expected, received);
	}

	@Test
	public void AssertDelete() {
		Optional<Category> c = Optional.of(new Category(1L, "fashion", 0.1));
		when(categoryRepository.findById(1L)).thenReturn(c);
		categoryService.deleteById(1L);
		when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> categoryService.findById(1L));
		verify(categoryRepository, times(2)).findById(1L);
		verify(categoryRepository, times(1)).deleteById(1L);
	}
}
