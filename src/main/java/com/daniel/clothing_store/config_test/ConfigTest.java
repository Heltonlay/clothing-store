package com.daniel.clothing_store.config_test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.daniel.clothing_store.entities.Category;
import com.daniel.clothing_store.entities.Clothing;
import com.daniel.clothing_store.entities.Employee;
import com.daniel.clothing_store.entities.Sale;
import com.daniel.clothing_store.entities.enums.PaymentMethod;
import com.daniel.clothing_store.repositories.CategoryRepository;
import com.daniel.clothing_store.repositories.ClothingRepository;
import com.daniel.clothing_store.repositories.EmployeeRepository;
import com.daniel.clothing_store.repositories.SaleRepository;

@Configuration
@Profile("test")
public class ConfigTest implements CommandLineRunner {

	@Autowired
	ClothingRepository clothingRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	SaleRepository saleRepository;

	@Override
	public void run(String... args) throws Exception {
		Category ct1 = new Category(null, "masculine");
		Category ct2 = new Category(null, "feminine");
		Category ct3 = new Category(null, "sportive");
		Category ct4 = new Category(null, "casual");
		Category ct5 = new Category(null, "comfort");

		categoryRepository.saveAll(Arrays.asList(ct1, ct2, ct3, ct4, ct5));

		Clothing cl1 = new Clothing(null, "masculine shirt white", 100.0, 15);
		Clothing cl2 = new Clothing(null, "feminine shirt blue", 100.0, 15);
		Clothing cl3 = new Clothing(null, "masculine pants casual", 100.0, 15);

		cl1.getCategories().addAll(Arrays.asList(ct1, ct3));
		cl2.getCategories().addAll(Arrays.asList(ct2, ct5));
		cl3.getCategories().addAll(Arrays.asList(ct1, ct4, ct5));

		clothingRepository.saveAll(Arrays.asList(cl1, cl2, cl3));

		ct1.getClothings().addAll(Arrays.asList(cl1, cl3));
		ct2.getClothings().add(cl2);
		ct3.getClothings().add(cl1);
		ct4.getClothings().add(cl3);
		ct5.getClothings().addAll(Arrays.asList(cl2, cl3));
		categoryRepository.saveAll(Arrays.asList(ct1, ct2, ct3, ct4, ct5));

		Employee e1 = new Employee(null, "John", new Date(), 1000.0);

		employeeRepository.saveAll(Arrays.asList(e1));

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = sdf1.parse("10/02/2023");
		Sale s1 = new Sale(null, PaymentMethod.PAYPAL, "João Bezerra", date1, e1, cl1, cl3);

		saleRepository.saveAll(Arrays.asList(s1));

		e1.getSales().add(s1);

		employeeRepository.saveAll(Arrays.asList(e1));

		cl1.getSales().add(s1);
		cl3.getSales().add(s1);

		clothingRepository.saveAll(Arrays.asList(cl1, cl3));
	}

}
