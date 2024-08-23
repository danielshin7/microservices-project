/*
 * Customer repository to handle CRUD functionality
 */
package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.object.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
