package com.echelon.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.echelon.model.Facility;

public interface FacilityRepository extends PagingAndSortingRepository<Facility, Long> {

}
