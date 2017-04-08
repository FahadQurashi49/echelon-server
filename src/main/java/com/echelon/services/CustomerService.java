package com.echelon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.echelon.dao.CustomerRepository;
import com.echelon.model.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public Page<Customer> getAllQueueCustomers(Long queueId, int pageNo, int size) {		
		return 
			customerRepository.findByQueueId(queueId, new PageRequest(pageNo, size));
	}
	
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	public Customer getCustomer (Long customerId) {
		return 
			customerRepository.findOne(customerId);
	}
	
	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	public void deleteCustomer(Long customerId) {
		customerRepository.delete(customerId);
	}
}
