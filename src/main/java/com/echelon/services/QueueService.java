package com.echelon.services;

import java.util.List;

import com.echelon.model.Customer;
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
	
	public Response<Queue> addQueue(Long facilityId, Queue queue) {
		queue.setFacility(new Facility(facilityId));
		return new Response<Queue>(queueRepository.save(queue)) ;
	}
	
	public Response<Queue> getQueue (Long queueId) {
		return 
			new Response<Queue>(queueRepository.findOne(queueId));
	}
	
	public Response<Queue> updateQueue(Long facilityId, Queue queue) {
		queue.setFacility(new Facility(facilityId));
		return new Response<Queue>(queueRepository.save(queue));
	}
	
	public void deleteQueue(Long queueId) {
		queueRepository.delete(queueId);
	}
	
/////////////////////////////////Business logic\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	private String getMsg(String msgId) {
		return messages.get(msgId);
	}
	
	public Response<Queue> runQueue(Long facilityId, Long queueId) {
		Response<Queue> resp = null;
		Queue queue = queueRepository.findByIdAndFacilityId(queueId, facilityId);		
		if (queue != null) {
			List<Queue> queues = queueRepository.findByFacilityIdAndIsRunning(facilityId, true);
			if (queues.size() == 0) {			
				queue.run();
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
			resp = new Response<>(Response.NOT_FOUND);
		}
		return resp;
	}

	public Response<Queue> cancelQueue (Long facilityId, Long queueId) {
		Response<Queue> resp = null;
		Queue queue = queueRepository.findByIdAndFacilityId(queueId, facilityId);
		if (queue != null) {
			if (queue.getIsRunning()) {
				queue.cancel();
				queueRepository.save(queue);
				resp = new Response<>(queue);
			} else {
				resp = new Response<>(Response.CONFLICT, getMsg("queue.cancel.conflict"), queue);
			}
		} else {
			resp = new Response<>(Response.NOT_FOUND);
		}
		return resp;
	}

	public Response<Queue> enqueueCustomer(Long facilityId, Long queueId, Customer customer) {
		Response<Queue> resp = null;
		Queue queue = queueRepository.findByIdAndFacilityId(queueId, facilityId);
		if (queue != null) {
			if (customer != null) {
				if (queue.getIsRunning()) {
					if (!customer.getInQueue()) {
						queue.enqueue(customer);
						queueRepository.save(queue);
						resp = new Response<>(getMsg("queue.enqueue.enqueued"), queue);
					} else {
						resp = new Response<>(Response.CONFLICT, getMsg("queue.enqueue.customer_conflict"), customer.getQueue());
					}
				} else {
					resp = new Response<Queue>(Response.CONFLICT, getMsg("queue.enqueue.queue_conflict"), queue);
				}
			} else {
				resp = new Response<>(Response.NOT_FOUND, getMsg("queue.enqueue.customer_not_found") );
			}
		} else {
			resp = new Response<>(Response.NOT_FOUND, getMsg("queue.enqueue.queue_not_found"));
		}
		return resp;
	}
}
