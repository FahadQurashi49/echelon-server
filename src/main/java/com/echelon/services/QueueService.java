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
		Queue queue = getQueueByFacility(queueId, facilityId);
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
		Queue queue = getQueueByFacility(queueId, facilityId);
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
		return queue;
	}
	
	public Queue cancelQueue (Long facilityId, Long queueId) {
		Queue queue = getQueueByFacility(queueId, facilityId);
		if (queue.getIsRunning()) {
			queue.cancel();
			queueRepository.save(queue);
		} else {
			throwStateConflictException("queue.cancel.conflict");
		}

		return queue;
	}
	
	public Customer enqueueCustomer(Long facilityId, Long queueId, Customer customer) {
		Queue queue = getQueueByFacility(queueId, facilityId);

 		if (checkConstraint(queue, customer, true)) {
			customer = queue.enqueue(customer);
			queueRepository.save(queue);
		}
		return customer;
	}

	public Customer dequeueCustomer(Long facilityId, Long queueId, Customer customer) {
		Queue queue = getQueueByFacility(queueId, facilityId);

		if (checkConstraint(queue,customer, false)) {
			customer = queue.dequeue(customer);
			queueRepository.save(queue);
		}

		return customer;
	}

	/////////////////////////////////Support methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	private Queue getQueueByFacility (Long queueId, Long facilityId) {
		Queue queue = queueRepository.findByIdAndFacilityId(queueId, facilityId);
		throwNotFoundException(queue, Queue.class);
		return queue;
	}

	private boolean checkConstraint (Queue queue, Customer customer, boolean isEnqueue) {
		final String FUNCTION_NAME = "queue." + (isEnqueue? "en": "de") + "queue.";
		String msgId;
		throwNotFoundException(customer, Customer.class);

		if (queue.getIsRunning()) {
			if (isEnqueue) {
				if (!customer.getIsInQueue()) {
					return true;
				} else msgId = "customer_conflict";
			} else {
				if (customer.getIsInQueue() && customer.getQueue().getId().equals(queue.getId())) {
					if (customer.getQueueNumber().equals(queue.getFront() + 1)) {
						return true;
					} else msgId = "not_in_front";
				} else msgId = "customer_conflict";
			}
		} else msgId = "queue_conflict";

		msgId = FUNCTION_NAME + msgId;
		throwStateConflictException(msgId);

		return false;
	}

}
