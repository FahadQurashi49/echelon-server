package com.echelon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.echelon.dao.CustomerRepository;
import com.echelon.model.Customer;
import com.echelon.model.Queue;

@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(method=RequestMethod.POST, value="/customer")
	public void addCustomer(@RequestBody Customer customer) {
		customerRepository.save(customer);
	} 
	
	@RequestMapping("queue/{id}/customer")
	public void registerInQueue (@PathVariable Long id, @RequestBody Customer customer ) {
		customer.setQueue(new Queue(id));
		customerRepository.save(customer);
	}
}
