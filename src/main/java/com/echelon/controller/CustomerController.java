package com.echelon.controller;

import com.echelon.response.Response;
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

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping( method=RequestMethod.POST, value="/customer")
	public Response<Customer> addCustomer (@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@RequestMapping("/customer/{id}")
	public Response<Customer> getCustomer(@PathVariable Long id) {
		return customerService.getCustomer(id);
	}
	@RequestMapping( method=RequestMethod.PUT, value="/customer")
	public Response<Customer> updateCustomer(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}
	@RequestMapping( method=RequestMethod.DELETE, value="/customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="faclity/{facilityId}/queue/{queueId}/customer")
	public Page<Customer> getAllQueueCustomers(
			@PathVariable Long facilityId,
			@PathVariable Long queueId,
			@RequestParam(required=false, defaultValue="0", value="page") int page, 
			@RequestParam(required=false, defaultValue="10", value="size" ) int size) {
		return customerService.getAllQueueCustomers(queueId, page, size);
	}
	
	
}
