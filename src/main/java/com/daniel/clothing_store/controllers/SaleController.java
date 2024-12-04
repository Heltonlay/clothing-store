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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniel.clothing_store.entities.Sale;
import com.daniel.clothing_store.services.ClothingService;
import com.daniel.clothing_store.services.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	@Autowired
	private ClothingService clothingService;

	@GetMapping
	public ResponseEntity<List<Sale>> findAllSales() {
		List<Sale> sales = service.findAll();
		return ResponseEntity.ok().body(sales);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Sale> findSaleByEmployeeId(@PathVariable Long id) {
		Sale sale = service.findByEmployeeId(id);
		return ResponseEntity.ok().body(sale);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Sale> findSaleById(@PathVariable Long id) {
		Sale sale = service.findById(id);
		return ResponseEntity.ok().body(sale);
	}

	@PostMapping
	public ResponseEntity<URI> insert(@RequestBody Sale sale) {
		double value = sale.getClothings().stream()
				.map(x -> clothingService.findById(x.getId()).getPrice())
				.reduce((x, y) -> x + y).get();
		sale.setValue(value);
		
		Long id = service.insert(sale);
		URI path = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.status(HttpStatus.CREATED).body(path);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Sale sale) {
		service.findById(id);
		sale.setId(id);
		service.update(sale);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
