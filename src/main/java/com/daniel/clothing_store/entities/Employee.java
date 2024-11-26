package com.daniel.clothing_store.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Date admissionDate;
	private Double baseSalary;

	@OneToMany
	private Map<Date, Sale> sales = new HashMap<>();

	public Employee() {
	}

	public Employee(Long id, String name, Date admissionDate, Double baseSalary) {
		this.id = id;
		this.name = name;
		this.admissionDate = admissionDate;
		this.baseSalary = baseSalary;
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

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Double getCommission(Date date) {
		return sales.get(date).getEmployeeCommission();
	}

	public void addSale(Date date, Sale sale) {
		this.sales.put(date, sale);
	}

	public void removeSale(Date date) {
		this.sales.remove(date);
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
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id);
	}

}
