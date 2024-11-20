package com.daniel.clothing_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.clothing_store.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}