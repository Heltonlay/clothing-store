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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private PaymentMethod paymentType;
	private String customer;
	private Double value = 0.0;
	private Date date;
	@Transient
	private Double employeeCommission;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@ManyToMany
	@JoinTable(name = "sale_clothing", joinColumns = @JoinColumn(name = "sale_id"), inverseJoinColumns = @JoinColumn(name = "clothing_id"))
	private List<Clothing> clothings = new ArrayList<>();

	public Sale() {
	}

	public Sale(Long id, PaymentMethod paymentType, String customer, Date date, Employee employee,
			Clothing... clothings) {
		this.id = id;
		this.paymentType = paymentType;
		this.customer = customer;
		this.date = date;
		this.employee = employee;
		this.clothings = new ArrayList<>();
		this.clothings.addAll(Arrays.asList(clothings));
		this.value = this.clothings.stream().map(x -> x.getPrice()).reduce((x, y) -> x + y).get();
		if (value == null) {
			this.value = 0.0;
		}
	}

	public Double getEmployeeCommission() {
		double percentage = 0.0;

		for (int i = 0; i < clothings.size(); i++) {
			for (int j = 0; j < clothings.get(i).getCategories().size(); j++) {
				double comissionPercentage = clothings.get(i).getCategories().get(j).getCommissionPercentage();
				if (comissionPercentage > percentage) {
					percentage = comissionPercentage;
				}
			}
		}

		employeeCommission = value * percentage;
		return employeeCommission;
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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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
