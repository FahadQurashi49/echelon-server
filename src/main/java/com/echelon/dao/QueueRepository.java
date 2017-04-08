package com.echelon.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.echelon.model.Queue;

public interface QueueRepository extends PagingAndSortingRepository<Queue, Long> {
	Page<Queue> findByFacilityId(Long facilityId, Pageable pageable);
	List<Queue> findByFacilityIdAndIsRunning(Long facilityId, Boolean isRunning);
	Queue findByIdAndFacilityId(Long id, Long facilityId);
}
