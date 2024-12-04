package com.daniel.clothing_store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.clothing_store.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	public Optional<Sale> findByEmployeeId(Long id);
}
