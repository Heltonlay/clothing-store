package com.daniel.clothing_store.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Sale {
	
	@Id
	private Long id;
}
