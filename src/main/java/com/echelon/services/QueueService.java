package com.echelon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.echelon.dao.QueueRepository;
import com.echelon.model.Facility;
import com.echelon.model.Queue;

@Service
public class QueueService {
	@Autowired
	private QueueRepository queueRepository;
	
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
	
	public Queue runQueue(Long facilityId, Long queueId) {
		
		Queue queue = queueRepository.findByIdAndFacilityId(queueId, facilityId);
		if (queue != null) {
			List<Queue> queues = queueRepository.findByFacilityIdAndIsRunning(facilityId, true);
			if (queues.size() == 0) {			
				queue.setIsRunning(true);
				updateQueue(facilityId, queue);			
			} else if (queues.size() == 1) {
				queue = queues.get(0);
			} else if (queues.size() > 1) {
				// TODO: handle error
				System.out.println("someone has messed up with the system");
				System.out.println("more than one queue is running of facilityId: " + facilityId);
			}
		}
		
		// TODO: handle all error
		// e.g. if queue doesn't exists
		// if facilityId doesn't exists etc.
		
		return queue;
	}
}
