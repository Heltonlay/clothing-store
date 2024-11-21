package com.daniel.clothing_store.entities;

import java.util.Date;
import java.util.Objects;

import com.daniel.clothing_store.entities.enums.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private PaymentMethod paymentType;
	private Double value;
	private Date date;
	//private List<Product> products;

	public Sale() {
	}

	public Sale(Long id, PaymentMethod paymentType, Double value, Date date) {
		this.id = id;
		this.paymentType = paymentType;
		this.value = value;
		this.date = date;
		//this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentMethod getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentMethod paymentType) {
		this.paymentType = paymentType;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	//public List<Product> getProducts() {
		//return products;
	//}

	public void addProducts(Product... products) {
		//this.products.addAll(Arrays.asList(products));
	}

	public void removeProducts(Product... products) {
		//this.products.removeAll(Arrays.asList(products));
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
		Sale other = (Sale) obj;
		return Objects.equals(id, other.id);
	}

}
