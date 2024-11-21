package com.daniel.clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.clothing_store.entities.Sale;
import com.daniel.clothing_store.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public List<Sale> findAll() {
		return repository.findAll();
	}

	public Sale findById(Long id) {
		Optional<Sale> sale = repository.findById(id);
		if (sale.isPresent())
			return sale.get();

		throw new RuntimeException();
	}

	public Long insert(Sale product) {
		repository.save(product);
		return product.getId();
	}

	public void update(Sale updatedSale) {
		Sale sale = findById(updatedSale.getId());
		sale.setValue(updatedSale.getValue());
		sale.setDate(updatedSale.getDate());
		sale.setPaymentType(updatedSale.getPaymentType());
		repository.save(sale);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
