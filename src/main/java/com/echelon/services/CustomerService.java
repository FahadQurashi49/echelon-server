package com.echelon.services;

import com.echelon.model.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.echelon.dao.CustomerRepository;
import com.echelon.model.Customer;

@Service
public class CustomerService extends BaseService{
	@Autowired
	private CustomerRepository customerRepository;

	// done (Y)
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	// done (Y)
	public Customer getCustomer (Long customerId) {
		Customer customer = customerRepository.findOne(customerId);
		throwNotFoundException(customer,Customer.ENTITY_CODE, Customer.class.getSimpleName());
		return customer;
	}
	// done (Y)
	public Customer updateCustomer(Customer customer) {
		 return	customerRepository.save(customer);
	}
	// done (Y)
	public void deleteCustomer(Long customerId) {
		customerRepository.delete(customerId);
	}

	/////////////////////////////////Business logic\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	// done (Y)
	public Page<Customer> getAllQueueCustomers(Long facilityId, Queue queue, int pageNo, int size) {
		Page<Customer> customers = null;
		if (queue != null && queue.getFacility().getId().equals(facilityId)) {
			customers = customerRepository.findByQueueId(queue.getId(), new PageRequest(pageNo, size));
		} else {
			throwNotFoundException("customer.queue_customers.queue_not_found");
		}
		return customers;
	}


}
