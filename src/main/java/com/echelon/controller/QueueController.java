package com.echelon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.echelon.dao.QueueRepository;
import com.echelon.model.Facility;
import com.echelon.model.Queue;

@RestController
public class QueueController {
	@Autowired
	private QueueRepository queueRepository;
	
	@RequestMapping(method=RequestMethod.POST, value="facility/{id}/queue")
	public void addQueue(@RequestBody Queue queue, @PathVariable Long id) {
		queue.setFacility(new Facility(id));
		queueRepository.save(queue);
	}
}
