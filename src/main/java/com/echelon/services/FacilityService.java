package com.echelon.services;

import com.echelon.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.echelon.dao.FacilityRepository;
import com.echelon.model.Facility;

@Service
public class FacilityService {
	@Autowired
	private FacilityRepository facilityRepository;
	
	public Page<Facility> getAllFacilities(int pageNo, int size) {		
		return 
			facilityRepository.findAll(new PageRequest(pageNo, size));
	}
	
	public Response<Facility> addFacility(Facility facility) {
		return new Response<Facility>(facilityRepository.save(facility));
	}

	public Response<Facility> getFacility (Long facilityId) {
		return new Response<Facility>(facilityRepository.findOne(facilityId));
	}
	
	public Response<Facility> updateFacility(Facility facility) {
		return new Response<Facility>(facilityRepository.save(facility));
	}
	
	public void deleteFacility(Long facilityId) {
		facilityRepository.delete(facilityId);
	}
}
