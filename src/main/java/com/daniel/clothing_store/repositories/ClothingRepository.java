package com.daniel.clothing_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daniel.clothing_store.entities.Clothing;

@Repository
public interface ClothingRepository extends JpaRepository<Clothing, Long> {
	
	@Query(value = "SELECT * FROM clothing WHERE name ~ ?1", nativeQuery = true)
	public List<Clothing> findByName(String name);
}
