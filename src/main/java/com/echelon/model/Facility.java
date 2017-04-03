package com.echelon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Facility {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facility")
	private List<Queue> queues;
	public Facility() {
		 
	}
	public Facility(Long id, String name, List<Queue> queues) {
		this.id = id;
		this.name = name;
		this.queues = queues;
	}
	public Facility(Long id) {
		this.id = id;		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Queue> getQueues() {
		return queues;
	}
	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}
	
	@Override
	public String toString() {
		return "ServiceProvider [id=" + id + ", name=" + name + "]";
	}	
}
