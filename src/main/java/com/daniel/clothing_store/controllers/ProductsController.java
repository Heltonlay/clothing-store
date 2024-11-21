package com.daniel.clothing_store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.clothing_store.entities.Product;
import com.daniel.clothing_store.services.ProductService;

@RestController
@RequestMapping("/clothes")
public class ProductsController {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<List<Product>> findAllProducts() {
		List<Product> products = service.findAll();
		return ResponseEntity.ok().body(products);
	}

	@GetMapping("/{name}")
	public ResponseEntity<List<Product>> findProductByName(@RequestParam String name) {
		List<Product> product = service.findByName(name);
		return ResponseEntity.ok().body(product);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> findProductById(@RequestParam Long id) {
		Product product = service.findById(id);
		return ResponseEntity.ok().body(product);
	}

	@PostMapping
	public ResponseEntity<Long> insert(@RequestBody Product product) {
		Long id = service.insert(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Product product, @RequestParam Long id) {
		service.findById(id);
		service.update(product);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteById(@RequestParam Long id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
