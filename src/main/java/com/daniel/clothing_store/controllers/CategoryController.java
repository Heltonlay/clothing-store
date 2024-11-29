package com.daniel.clothing_store.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<List<Category>> findCategoryByName(@RequestParam String name) {
		List<Category> category = service.findByName(name);
		return ResponseEntity.ok().body(category);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
		Category category = service.findById(id);
		return ResponseEntity.ok().body(category);
	}

	@PostMapping
	public ResponseEntity<URI> insert(@RequestBody Category category) {
		Long id = service.insert(category);
		URI path = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(path);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Category category) {
		service.findById(id);
		category.setId(id);
		service.update(category);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
