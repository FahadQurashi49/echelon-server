package com.echelon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private Long queueNumber;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="queue_id")
	private Queue queue;
	
	public Customer() {
		
	}
	public Customer(Long id, String name, Long queueNumber) {		
		this.id = id;
		this.name = name;
		this.queueNumber = queueNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQueueNumber() {
		return queueNumber;
	}

	public void setQueueNumber(Long queueNumber) {
		this.queueNumber = queueNumber;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Queue getQueue() {
		return queue;
	}
	public void setQueue(Queue queue) {
		this.queue = queue;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", queueNumber=" + queueNumber + "]";
	}
}
