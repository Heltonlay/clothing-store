package com.daniel.clothing_store.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.daniel.clothing_store.entities.enums.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private PaymentMethod paymentType;
	private Double value;
	private Date date;

	@ManyToOne
	private Employee employee;
	@ManyToMany
	private List<Clothing> clothings = new ArrayList<>();

	public Sale() {
	}

	public Sale(Long id, PaymentMethod paymentType, Double value, Date date, Employee employee) {
		this.id = id;
		this.paymentType = paymentType;
		this.value = value;
		this.date = date;
		this.employee = employee;
	}

	public Double getEmployeeCommission() {
		return value * 0.1;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Clothing> getClothings() {
		return clothings;
	}

	public void addClothings(Clothing... clothings) {
		this.clothings.addAll(Arrays.asList(clothings));
	}

	public void addClothings(List<Clothing> clothings) {
		this.clothings.addAll(clothings);
	}

	public void removeClothings(Clothing... clothings) {
		this.clothings.removeAll(Arrays.asList(clothings));
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
