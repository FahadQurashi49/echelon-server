package com.echelon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.echelon.dao.FacilityRepository;
import com.echelon.model.Facility;

@Service
public class FacilityService extends BaseService {
	@Autowired
	private FacilityRepository facilityRepository;

	public Page<Facility> getAllFacilities(int pageNo, int size) {		
		return 
			facilityRepository.findAll(new PageRequest(pageNo, size));
	}

	public Facility addFacility(Facility facility) {
		return facilityRepository.save(facility);
	}

	public Facility getFacility (Long facilityId) {
		Facility facility = facilityRepository.findOne(facilityId);
		throwNotFoundException(facility, Facility.class);
		return facility;
	}

	public Facility updateFacility(Facility facility) {
		return facilityRepository.save(facility);
	}

	public void deleteFacility(Long facilityId) {
		facilityRepository.delete(facilityId);
	}
}
