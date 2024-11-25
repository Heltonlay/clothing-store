package com.daniel.clothing_store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.clothing_store.entities.Category;
import com.daniel.clothing_store.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<Category>> findAllCategories() {
		List<Category> categories = service.findAll();
		return ResponseEntity.ok().body(categories);
	}

	@GetMapping(params = "name")
	public ResponseEntity<List<Category>> findProductByName(@RequestParam String name) {
		List<Category> product = service.findByName(name);
		return ResponseEntity.ok().body(product);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
		Category category = service.findById(id);
		return ResponseEntity.ok().body(category);
	}

	@PostMapping
	public ResponseEntity<Long> insert(@RequestBody Category category) {
		Long id = service.insert(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Category category, @RequestParam Long id) {
		service.findById(id);
		service.update(category);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteById(@RequestParam Long id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
