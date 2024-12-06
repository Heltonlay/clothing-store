package com.daniel.clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.clothing_store.entities.Category;
import com.daniel.clothing_store.exceptions.NotFoundException;
import com.daniel.clothing_store.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll() {
		return repository.findAll();
	}

	public List<Category> findByName(String name) {
		return repository.findByName(name);
	}

	public Category findById(Long id) {
		Optional<Category> category = repository.findById(id);
		if (category.isPresent())
			return category.get();

		throw new NotFoundException("Id not found or does not exist.");
	}

	public Long insert(Category product) {
		repository.save(product);
		return product.getId();
	}

	public void update(Category updatedCategory) {
		Category category = findById(updatedCategory.getId());
		category.setName(updatedCategory.getName());
		repository.save(category);
	}

	public void deleteById(Long id) {
		if(id == null)
			throw new IllegalArgumentException("Id given is null.");
		
		findById(id);
		repository.deleteById(id);
	}
}
