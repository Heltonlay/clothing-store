package com.daniel.clothing_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daniel.clothing_store.entities.Clothing;

@Repository
public interface ClothingRepository extends JpaRepository<Clothing, Long> {
	
	@Query(value = "SELECT * FROM clothing WHERE name ~ ?1", nativeQuery = true)
	public List<Clothing> findByName(String name);
	
	@Query(value = 
			"SELECT * FROM clothing WHERE name ~ ?1 AND id = ANY (SELECT DISTINCT clothing_id "
			+ "FROM clothing_categories WHERE categories_id = ANY (SELECT id "
			+ "FROM category WHERE name IN (?2, ?3, ?4)))"
			, nativeQuery = true)
	public List<Clothing> findByNameAndCategories(String name, String cat1, String cat2, String cat3);
}
