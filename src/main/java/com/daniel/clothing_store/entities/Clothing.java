package com.daniel.clothing_store.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "clothing")
public class Clothing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	private Integer quantityInStock;
	@Transient
	private Integer salesQuantity;

	@JsonIgnore
	@ManyToMany(mappedBy = "clothings")
	private List<Sale> sales = new ArrayList<>();
	@ManyToMany
	@JoinTable(name = "clothing_category", joinColumns = @JoinColumn(name = "clothing_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories = new ArrayList<>();

	public Clothing() {
	}

	public Clothing(Long id, String name, Double price, Integer quantityInStock) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}

	public Clothing(Long id, String name, Double price, Integer quantityInStock, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantityInStock = quantityInStock;
		this.categories = categories;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public Integer getSalesQuantity() {
		salesQuantity = sales.size();
		return salesQuantity;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public List<Category> getCategories() {
		return categories;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clothing other = (Clothing) obj;
		return Objects.equals(id, other.id);
	}

}
