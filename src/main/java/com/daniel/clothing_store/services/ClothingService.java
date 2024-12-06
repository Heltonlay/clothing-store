package com.daniel.clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.clothing_store.entities.Clothing;
import com.daniel.clothing_store.exceptions.NotFoundException;
import com.daniel.clothing_store.repositories.ClothingRepository;

@Service
public class ClothingService {

	@Autowired
	private ClothingRepository repository;

	public List<Clothing> findAll() {
		return repository.findAll();
	}

	public List<Clothing> findByName(String name) {
		return repository.findByName(name);
	}

	public List<Clothing> findByNameAndCategories(String name, String categories) {
		String[] splitCategories = categories.split(",");
		String[] cats = new String[5];
		for (int i = 0; i < 5; i++) {
			try {
				cats[i] = splitCategories[i];
			} catch (Exception e) {
				break;
			}
		}
		return repository.findByNameAndCategories(name, cats[0], cats[1], cats[2], cats[3], cats[4]);
	}

	public Clothing findById(Long id) {
		Optional<Clothing> clothing = repository.findById(id);
		if (clothing.isPresent())
			return clothing.get();

		throw new NotFoundException("Id not found or does not exist.");
	}

	public Long insert(Clothing product) {
		repository.save(product);
		return product.getId();
	}

	public void update(Clothing updatedClothing) {
		Clothing clothing = findById(updatedClothing.getId());
		clothing.setName(updatedClothing.getName());
		clothing.setPrice(updatedClothing.getPrice());
		clothing.setQuantityInStock(updatedClothing.getQuantityInStock());
		clothing.getCategories().clear();
		clothing.getCategories().addAll(updatedClothing.getCategories());
		repository.save(clothing);
	}

	public void deleteById(Long id) {
		if(id == null)
			throw new IllegalArgumentException("Id given is null.");
		
		findById(id);
		repository.deleteById(id);
	}
}
