package com.echelon.controller;

import com.echelon.model.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.echelon.model.Customer;
import com.echelon.services.CustomerService;

import javax.validation.Valid;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping( method=RequestMethod.POST, value="/customer")
	public Customer addCustomer (@RequestBody @Valid Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@RequestMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable Long id) {
		return customerService.getCustomer(id);
	}
	@RequestMapping( method=RequestMethod.PUT, value="/customer")
	public Customer updateCustomer(@RequestBody @Valid  Customer customer) {
		return customerService.updateCustomer(customer);
	}
	@RequestMapping( method=RequestMethod.DELETE, value="/customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/facility/{facilityId}/queue/{queueId}/customer")
	public Page<Customer> getAllQueueCustomers(
			@PathVariable Long facilityId,
			@PathVariable("queueId") Queue queue,
			@RequestParam(required=false, defaultValue="0", value="page") int page,
			@RequestParam(required=false, defaultValue="10", value="size" ) int size) {
		return customerService.getAllQueueCustomers(facilityId, queue, page, size);
	}
	
	
}
