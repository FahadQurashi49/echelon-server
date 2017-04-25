package com.echelon.services;

import java.util.List;
import java.util.stream.Collectors;

import com.echelon.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.echelon.dao.QueueRepository;
import com.echelon.model.Facility;
import com.echelon.model.Queue;

@Service
public class QueueService extends BaseService {
	@Autowired
	private QueueRepository queueRepository;
	
	/////////////////////////////////CRUD operations\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public Page<Queue> getAllQueues(Long facilityId, int pageNo, int size) {
		return 
			queueRepository.findByFacilityId(facilityId, new PageRequest(pageNo, size));
	}
	
	public Queue addQueue(Long facilityId, Queue queue) {
		queue.setFacility(new Facility(facilityId));
		return queueRepository.save(queue);
	}
	
	public Queue getQueue (Long facilityId, Long queueId) {
		Queue queue = queueRepository.findByIdAndFacilityId(queueId, facilityId);
		throwNotFoundException(queue,Queue.ENTITY_CODE, Queue.class.getSimpleName());
		return queue;
	}
	
	public Queue updateQueue(Long facilityId, Queue queue) {
		queue.setFacility(new Facility(facilityId));
		return queueRepository.save(queue);
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
				queue.run();
				updateQueue(facilityId, queue);
			} else if (queues.size() == 1) {
				queue = queues.get(0);
				String msgArgs = queue.getId().toString();
				throwStateConflictException("queue.run.conflict", msgArgs);
			} else if (queues.size() > 1) {
				// TODO: handle error
				System.out.println("someone has messed up with the system");
				System.out.println("more than one queue is running of facilityId: " + facilityId);
				String msgArgs =
						queues.stream()
								.map(q -> q.getId().toString())
								.collect(Collectors.joining(";"));
				throwStateConflictException("queue.run.multiple", msgArgs);
			}
		} else {
			throwNotFoundException("queue.run.not_found");
		}
		return queue;
	}
	
	public Queue cancelQueue (Long facilityId, Long queueId) {
		Queue queue = queueRepository.findByIdAndFacilityId(queueId, facilityId);
		if (queue != null) {
			if (queue.getIsRunning()) {
				queue.cancel();
				queueRepository.save(queue);
			} else {
				throwStateConflictException("queue.cancel.conflict");
			}
		} else {
			throwNotFoundException("queue.cancel.queue_not_found");
		}

		return queue;
	}
	
	public Queue enqueueCustomer(Long facilityId, Long queueId, Customer customer) {
		Queue queue = queueRepository.findByIdAndFacilityId(queueId, facilityId);

		if (queue != null) {
			if (customer != null) {
				if (queue.getIsRunning()) {
					if (!customer.getIsInQueue()) {
						queue.enqueue(customer);
						queueRepository.save(queue);
					} else {
						throwStateConflictException("queue.enqueue.customer_conflict");
					}
				} else {
					throwStateConflictException("queue.enqueue.queue_conflict");
				}
			} else {
				throwNotFoundException("queue.enqueue.customer_not_found");
			}
		} else {
			throwNotFoundException("queue.enqueue.queue_not_found");
		}
		return queue;
	}

}
