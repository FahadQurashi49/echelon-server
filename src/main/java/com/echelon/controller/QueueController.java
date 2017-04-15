package com.echelon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.echelon.dao.QueueRepository;
import com.echelon.model.Queue;
import com.echelon.response.Response;
import com.echelon.services.QueueService;

@RestController
public class QueueController {
	@Autowired
	private QueueService queueService;
	
	/////////////////////////////////CRUD Requests\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	@RequestMapping(method=RequestMethod.POST, value="facility/{facilityId}/queue")
	public Response<Queue> addQueue(@RequestBody Queue queue, @PathVariable Long facilityId) {
		return queueService.addQueue(facilityId, queue);
	}
	
	@RequestMapping("/facility/{facilityId}/queue/{queueId}")
	public Response<Queue> getQueue(@PathVariable Long facilityId, @PathVariable Long queueId) {
		return queueService.getQueue(queueId);
	}
	@RequestMapping( method=RequestMethod.PUT, value="/facility/{facilityId}/queue")
	public Response<Queue> updateQueue(@PathVariable Long facilityId, @RequestBody Queue queue) {
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
	
	/////////////////////////////////Business logic Requests\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	@RequestMapping(method=RequestMethod.GET, value="facility/{facilityId}/queue/{queueId}/run")
	public Response<Queue> runQueue(@PathVariable Long facilityId, @PathVariable Long queueId) {
		return queueService.runQueue(facilityId, queueId);		
	}
	
}
