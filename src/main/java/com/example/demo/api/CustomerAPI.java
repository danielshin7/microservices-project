//Customer API to handle CRUD functions
package com.example.demo.api;

import java.net.URI;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.object.Customer;
import com.example.demo.logger.Logger;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerAPI {
	@Autowired
	CustomerRepository repo;

	/**
	 * Get request for entire customer list
	 * @return list of customers from database
	 */
	@GetMapping
	public Iterable<Customer> getAll() {
		return repo.findAll();
	}
	
	/**
	 * Get request by customer id
	 * @param id
	 * @return specified customer by id
	 */
	@GetMapping("/{customerId}")
	public Optional<Customer> getCustomerById(@PathVariable("customerId") long id) {
		return repo.findById(id);
	}
	
	/**
	 * Post method to handle post call
	 * @param newCustomer
	 * @param uri
	 * @return response code to see if api is connected
	 */
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri) {
		if (newCustomer.getId() != 0 || newCustomer.getName() == null || newCustomer.getEmail() == null) {
			// Reject we'll assign the customer id
			return ResponseEntity.badRequest().build();
		}
		newCustomer = repo.save(newCustomer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newCustomer.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}

	/**
	 * Find customer by using Get request 
	 * @param username
	 * @param uri
	 * @return response code indicating api connection
	 */
	@GetMapping("/byname/{username}")
	public ResponseEntity<?> lookupCustomerByNameGet(@PathVariable("username") String username,
			UriComponentsBuilder uri) {
		Logger.log("username: " + username);
		
		Iterator<Customer> customers = repo.findAll().iterator();
		while(customers.hasNext()) {
			Customer cust = customers.next();
			if(cust.getName().equalsIgnoreCase(username)) {
				ResponseEntity<?> response = ResponseEntity.ok(cust);
				return response;				
			}			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	/**
	 * Find customer by Post request
	 * @param username
	 * @param uri
	 * @return response code for api connection
	 */
	@PostMapping("/byname")
	public ResponseEntity<?> lookupCustomerByNamePost(@RequestBody String username, UriComponentsBuilder uri) {
		Logger.log("username: " + username);
		Iterator<Customer> customers = repo.findAll().iterator();
		while(customers.hasNext()) {
			Customer cust = customers.next();
			if(cust.getName().equals(username)) {
				ResponseEntity<?> response = ResponseEntity.ok(cust);
				return response;				
			}			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}	
	
	/**
	 * Put request to edit customer data
	 * @param newCustomer
	 * @param customerId
	 * @return response code for api connection
	 */
	@PutMapping("/{customerId}")
	public ResponseEntity<?> putCustomer(
			@RequestBody Customer newCustomer,
			@PathVariable("customerId") long customerId) 
	{
		if (newCustomer.getId() != customerId || newCustomer.getName() == null || newCustomer.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		newCustomer = repo.save(newCustomer);
		return ResponseEntity.ok().build();
	}	
	
	/*
	 * Delete request to delete customer data
	 * @param id
	 * @return response on API connection
	 */
	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long id) {
		// repo.delete(id);
		repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}	
	
	
}
