package com.daniel.clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.clothing_store.entities.Sale;
import com.daniel.clothing_store.exceptions.NotFoundException;
import com.daniel.clothing_store.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public List<Sale> findAll() {
		return repository.findAll();
	}

	public Sale findByEmployeeId(Long id) {
		Optional<Sale> sale = repository.findByEmployeeId(id);
		if (sale.isPresent())
			return sale.get();

		throw new RuntimeException();
	}

	public Sale findById(Long id) {
		Optional<Sale> sale = repository.findById(id);
		if (sale.isPresent())
			return sale.get();

		throw new NotFoundException("Id not found or does not exist.");
	}

	public Long insert(Sale sale) {
		repository.save(sale);
		return sale.getId();
	}

	public void update(Sale updatedSale) {
		Sale sale = findById(updatedSale.getId());
		sale.setPaymentType(updatedSale.getPaymentType());
		sale.setDate(updatedSale.getDate());
		sale.setEmployee(updatedSale.getEmployee());
		sale.getClothings().clear();
		sale.getClothings().addAll(updatedSale.getClothings());
		repository.save(sale);
	}

	public void deleteById(Long id) {
		if(id == null)
			throw new IllegalArgumentException("Id given is null.");
		
		findById(id);
		repository.deleteById(id);
	}
}
