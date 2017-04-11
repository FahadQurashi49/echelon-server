package com.echelon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.echelon.dao.FacilityRepository;
import com.echelon.model.Facility;
import com.echelon.response.Messages;

@Service
public class FacilityService {
	@Autowired
	private FacilityRepository facilityRepository;
	
	@Autowired
    Messages messages;
	
	public Page<Facility> getAllFacilities(int pageNo, int size) {		
		return 
			facilityRepository.findAll(new PageRequest(pageNo, size));
	}
	
	public void addFacility(Facility facility) {
		facilityRepository.save(facility);
	}
	
	public Facility getFacility (Long facilityId) {
		System.out.println(messages.get("default.title"));
		return 
			facilityRepository.findOne(facilityId);
	}
	
	public void updateFacility(Facility facility) {
		facilityRepository.save(facility);
	}
	
	public void deleteFacility(Long facilityId) {
		facilityRepository.delete(facilityId);
	}
}
