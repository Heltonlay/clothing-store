package com.daniel.clothing_store.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clothes")
public class ProductsController {
	
	@GetMapping
	public ResponseEntity<String> Test() {
		return ResponseEntity.ok().body("Teste");
	}
	
}
