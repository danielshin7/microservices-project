//Customer API to test GET requests
package com.example.demo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerAPI{
	ArrayList<Customer> customers = new ArrayList<>();
	
	public CustomerAPI() {
		Customer c1 = new Customer(1, "Daniel", "pass", "daniel@example.com");
		Customer c2 = new Customer(2, "Kelsey", "pass", "kelsey@example.com");
		Customer c3 = new Customer(3, "Brianne", "pass", "brianne@example.com");
		Customer c4 = new Customer(4, "Elbie", "pass", "elbie@example.com");
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		customers.add(c4);
	}
	
	@GetMapping
	public String allCustomers() {
		String customerList = "[ ";
		for (int i = 0; i < customers.size(); i++) {
			customerList += customers.get(i).toJSON();
			if (i + 1 < customers.size()) {
				customerList += ", ";
			}
		}
		customerList += " ]";
		return customerList;
	}
	
	@GetMapping("/{customerId}")
	public String customerById(@PathVariable("customerId") long id){
		String customer = "{}";
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId() == id) {
				customer = customers.get(i).toJSON();
			}
		}
		return customer;
	}
}