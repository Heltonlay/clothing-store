package com.daniel.clothing_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daniel.clothing_store.entities.Clothing;

@Repository
public interface ClothingRepository extends JpaRepository<Clothing, Long> {
	
	@Query(value = "SELECT * FROM clothing WHERE LOWER(name) ~ LOWER(?1)", nativeQuery = true)
	public List<Clothing> findByName(String name);
	
	@Query(value = 
			"WITH tableCount AS (SELECT COUNT(*) FROM category WHERE name IN (?2,?3,?4,?5,?6)) "
			+ "SELECT * FROM clothing WHERE LOWER(name) ~ LOWER(?1) AND id = ANY (SELECT clothing_id FROM "
			+ "(SELECT clothing_id FROM clothing_category WHERE category_id = ANY(SELECT id FROM category "
			+ "WHERE name IN (?2,?3,?4,?5,?6))) GROUP BY clothing_id HAVING COUNT(*) >= (SELECT count FROM tableCount))"
			, nativeQuery = true)
	public List<Clothing> findByNameAndCategories(String name, String cat1, String cat2, String cat3, String cat4, String cat5);
}
