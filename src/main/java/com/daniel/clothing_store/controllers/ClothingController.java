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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniel.clothing_store.entities.Clothing;
import com.daniel.clothing_store.services.ClothingService;

@RestController
@RequestMapping("/clothings")
public class ClothingController {

	@Autowired
	private ClothingService service;

	@GetMapping
	public ResponseEntity<List<Clothing>> findAllProducts() {
		List<Clothing> products = service.findAll();
		return ResponseEntity.ok().body(products);
	}

	@GetMapping(params = "name")
	public ResponseEntity<List<Clothing>> findProductByName(@RequestParam String name) {
		List<Clothing> product = service.findByName(name);
		return ResponseEntity.ok().body(product);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Clothing> findProductById(@PathVariable Long id) {
		Clothing product = service.findById(id);
		return ResponseEntity.ok().body(product);
	}

	@PostMapping
	public ResponseEntity<URI> insert(@RequestBody Clothing product) {
		Long id = service.insert(product);
		URI path = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(id).toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(path);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Clothing product, @RequestParam Long id) {
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
