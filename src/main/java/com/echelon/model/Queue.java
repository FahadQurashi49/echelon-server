package com.echelon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Queue {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private Boolean isRunning;
	private Long currentNumber;
	// add cascade for remove
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="facility_id", nullable = false)
	private Facility facility;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="queue")
	private List<Customer> customers;
	
	public Queue() {
		
	}
	public Queue(Long id, String name, Boolean isRunning, Long currentNumber, Facility facility) {
		this.id = id;
		this.name = name;
		this.isRunning = isRunning;
		this.currentNumber = currentNumber;
		this.facility = facility;
	}
	
	public Queue(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsRunning() {
		return isRunning;
	}
	public void setIsRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}
	public Long getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(Long currentNumber) {
		this.currentNumber = currentNumber;
	}
	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}
	public Facility getFacility() {
		return facility;
	}
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	@Override
	public String toString() {
		return "Queue [id=" + id + ", name=" + name + ", isRunning=" + isRunning + ", currentNumber=" + currentNumber
				+ "]";
	}
}
