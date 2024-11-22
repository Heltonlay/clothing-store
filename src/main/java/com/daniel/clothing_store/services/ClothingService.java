package com.daniel.clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.clothing_store.entities.Clothing;
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

	public Clothing findById(Long id) {
		Optional<Clothing> clothing = repository.findById(id);
		if (clothing.isPresent())
			return clothing.get();

		throw new RuntimeException();
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
		repository.save(clothing);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
