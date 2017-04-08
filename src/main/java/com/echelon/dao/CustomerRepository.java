package com.echelon.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.echelon.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	Page<Customer> findByQueueId(Long queueId, Pageable page);
}
