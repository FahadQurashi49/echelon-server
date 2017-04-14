package com.echelon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.echelon.dao.QueueRepository;
import com.echelon.model.Facility;
import com.echelon.model.Queue;
import com.echelon.response.Messages;
import com.echelon.response.Response;

@Service
public class QueueService {
	@Autowired
	private QueueRepository queueRepository;
	@Autowired
    private Messages messages;
	
	/////////////////////////////////CRUD operations\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public Page<Queue> getAllQueues(Long facilityId, int pageNo, int size) {		
		return 
			queueRepository.findByFacilityId(facilityId, new PageRequest(pageNo, size));
	}
	
	public void addQueue(Long facilityId, Queue queue) {
		queue.setFacility(new Facility(facilityId));
		queueRepository.save(queue);
	}
	
	public Queue getQueue (Long queueId) {
		return 
			queueRepository.findOne(queueId);
	}
	
	public void updateQueue(Long facilityId, Queue queue) {
		queue.setFacility(new Facility(facilityId));
		queueRepository.save(queue);
	}
	
	public void deleteQueue(Long queueId) {
		queueRepository.delete(queueId);
	}
	
/////////////////////////////////Business logic\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	private String getMsg(String msgId) {
		return messages.get(msgId);
	}
	
	public Response<Queue> runQueue(Long facilityId, Long queueId) {
		Response<Queue> resp= null;
		Queue queue = queueRepository.findByIdAndFacilityId(queueId, facilityId);		
		if (queue != null) {
			List<Queue> queues = queueRepository.findByFacilityIdAndIsRunning(facilityId, true);
			if (queues.size() == 0) {			
				queue.setIsRunning(true);				
				updateQueue(facilityId, queue);
				resp = new Response<>(getMsg("queue.run.running"), queue);				
			} else if (queues.size() == 1) {
				queue = queues.get(0);				
				resp = new Response<>(Response.CONFLICT, getMsg("queue.run.conflict"), queue);
				
			} else if (queues.size() > 1) {
				// TODO: handle error
				System.out.println("someone has messed up with the system");
				System.out.println("more than one queue is running of facilityId: " + facilityId);
				
				resp = new Response<>(Response.ERROR, getMsg("queue.run.multiple"));
			}
		} else {
			resp = new Response<>(Response.NOT_FOUND, getMsg("queue.run.not_found"));
		}
		
		// TODO: handle all error
		// e.g. if queue doesn't exists
		// if facilityId doesn't exists etc.
		
		return resp;
	}
}
