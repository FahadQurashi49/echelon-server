package com.echelon.services;

import com.echelon.response.Response;
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
	
	public Response<Customer> addCustomer(Customer customer) {
		return new Response<Customer>(customerRepository.save(customer));
	}
	
	public Response<Customer> getCustomer (Long customerId) {
		return 
			new Response<Customer>(customerRepository.findOne(customerId));
	}
	
	public Response<Customer> updateCustomer(Customer customer) {
		 return	new Response<Customer>(customerRepository.save(customer));
	}
	
	public void deleteCustomer(Long customerId) {
		customerRepository.delete(customerId);
	}
}
