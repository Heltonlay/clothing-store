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

import com.daniel.clothing_store.entities.Sale;
import com.daniel.clothing_store.services.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping
	public ResponseEntity<List<Sale>> findAllSales() {
		List<Sale> sales = service.findAll();
		return ResponseEntity.ok().body(sales);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Sale> findSaleById(@PathVariable Long id) {
		Sale sale = service.findById(id);
		return ResponseEntity.ok().body(sale);
	}

	@PostMapping
	public ResponseEntity<URI> insert(@RequestBody Sale sale) {
		Long id = service.insert(sale);
		URI path = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(id).toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(path);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Sale sale, @RequestParam Long id) {
		service.findById(id);
		service.update(sale);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteById(@RequestParam Long id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
