package com.echelon.dao;

import org.springframework.data.repository.CrudRepository;

import com.echelon.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
