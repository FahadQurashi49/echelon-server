package com.echelon.controller;

import com.echelon.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.echelon.model.Queue;
import com.echelon.services.QueueService;

import javax.validation.Valid;

@RestController
public class QueueController {
	@Autowired
	private QueueService queueService;
	
	/////////////////////////////////CRUD Requests\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	@RequestMapping(method=RequestMethod.POST, value="facility/{facilityId}/queue")
	public Queue addQueue(@RequestBody @Valid Queue queue, @PathVariable Long facilityId) {
		return queueService.addQueue(facilityId, queue);
	}
	
	@RequestMapping("/facility/{facilityId}/queue/{queueId}")
	public Queue getQueue(@PathVariable Long facilityId, @PathVariable Long queueId) {
		return queueService.getQueue(facilityId, queueId);
	}
	@RequestMapping( method=RequestMethod.PUT, value="/facility/{facilityId}/queue")
	public Queue updateQueue(@PathVariable Long facilityId, @RequestBody @Valid Queue queue) {
		return queueService.updateQueue(facilityId, queue);
	}
	@RequestMapping( method=RequestMethod.DELETE, value="/facility/{facilityId}/queue/{queueId}")
	public void deleteQueue(@PathVariable Long facilityId, @PathVariable Long queueId) {
		queueService.deleteQueue(queueId);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="facility/{facilityId}/queue")
	public Page<Queue> getAllQueues (
			@PathVariable Long facilityId,
			@RequestParam(required=false, defaultValue="0", value="page") int page, 
			@RequestParam(required=false, defaultValue="10", value="size" ) int size) {
		return queueService.getAllQueues(facilityId, page, size);
	}
	
	/////////////////////////////////Business logic Requests\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	@RequestMapping(method=RequestMethod.GET, value="facility/{facilityId}/queue/{queueId}/run")
	public Queue runQueue(@PathVariable Long facilityId, @PathVariable Long queueId) {
		return queueService.runQueue(facilityId, queueId);		
	}
	@RequestMapping(method=RequestMethod.GET, value="facility/{facilityId}/queue/{queueId}/cancel")
	public Queue cancelQueue(@PathVariable Long facilityId, @PathVariable Long queueId) {
		return queueService.cancelQueue(facilityId, queueId);
	}
	@RequestMapping(method=RequestMethod.GET,
			value="facility/{facilityId}/queue/{queueId}/customer/{customerId}/enqueue")
	public Customer enqueueCustomer(@PathVariable Long facilityId,
									@PathVariable Long queueId,
									@PathVariable("customerId") Customer customer ) {
		return queueService.enqueueCustomer(facilityId, queueId, customer);
	}
	@RequestMapping(method=RequestMethod.GET,
			value="facility/{facilityId}/queue/{queueId}/customer/{customerId}/dequeue")
	public Customer dequeueCustomer(@PathVariable Long facilityId,
									@PathVariable Long queueId,
									@PathVariable("customerId") Customer customer ) {
		return queueService.dequeueCustomer(facilityId, queueId, customer);
	}
}
