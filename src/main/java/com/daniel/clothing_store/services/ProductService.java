package com.daniel.clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.clothing_store.entities.Product;
import com.daniel.clothing_store.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> findAll() {
		return repository.findAll();
	}

	public List<Product> findByName(String name) {
		return repository.findByName(name);
	}

	public Product findById(Long id) {
		Optional<Product> product = repository.findById(id);
		if (product.isPresent())
			return product.get();

		throw new RuntimeException();
	}

	public Long insert(Product product) {
		repository.save(product);
		return product.getId();
	}

	public void update(Product updatedProduct) {
		Product product = findById(updatedProduct.getId());
		product.setName(updatedProduct.getName());
		product.setPrice(updatedProduct.getPrice());
		product.setQuantityInStock(updatedProduct.getQuantityInStock());
		product.setSales(updatedProduct.getSales());
		repository.save(product);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
