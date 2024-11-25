package com.daniel.clothing_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.clothing_store.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	public List<Category> findByName(String name);
}
