package com.echelon.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"queue_id", "queueNumber"})
)
@Entity
@JsonIgnoreProperties(value = {"queueNumber", "isInQueue"}, allowGetters = true)
public class Customer {
	// for error code generation
	@JsonIgnore
	public static final int ENTITY_CODE = 3;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min=2, max=30)
	private String name;
	private Long queueNumber = 0L;
	private Boolean isInQueue = false;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="queue_id")
	private Queue queue;
	
	public Customer() {
		
	}

	public Customer(Long id, String name, Long queueNumber, Boolean isInQueue) {
		this.id = id;
		this.name = name;
		this.queueNumber = queueNumber;
		this.isInQueue = isInQueue;
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

	public Boolean getIsInQueue() {
		return isInQueue;
	}

	public void setIsInQueue(Boolean inQueue) {
		isInQueue = inQueue;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", queueNumber=" + queueNumber + "]";
	}
}
