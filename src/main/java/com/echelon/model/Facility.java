package com.echelon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Facility {
	// for error code generation
	@JsonIgnore
	public static final int ENTITY_CODE = 1;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min=2, max=30)
	private String name;
	
	@JsonIgnore
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
