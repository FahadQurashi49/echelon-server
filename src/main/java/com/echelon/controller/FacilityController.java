package com.echelon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.echelon.model.Facility;
import com.echelon.services.FacilityService;

import javax.validation.Valid;

@RestController
public class FacilityController {
	@Autowired
	private FacilityService facilityService;
	
	@RequestMapping( method=RequestMethod.POST, value="/facility")
	public Facility addFacility (@RequestBody @Valid Facility facility) {
		return facilityService.addFacility(facility);
	}
	
	@RequestMapping("/facility/{id}")
	public Facility getFacility(@PathVariable Long id) {
		return facilityService.getFacility(id);
	}
	@RequestMapping( method=RequestMethod.PUT, value="/facility")
	public Facility updateFacility(@RequestBody @Valid Facility facility) {
		return facilityService.updateFacility(facility);
	}
	@RequestMapping( method=RequestMethod.DELETE, value="/facility/{id}")
	public void deleteFacility(@PathVariable Long id) {
		facilityService.deleteFacility(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="facility")
	public Page<Facility> getAllFacilities(
			@RequestParam(required=false, defaultValue="0", value="page") int page, 
			@RequestParam(required=false, defaultValue="10", value="size" ) int size) {
		return facilityService.getAllFacilities(page, size);
	}
	
	
}
