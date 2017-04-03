package com.echelon.dao;

import org.springframework.data.repository.CrudRepository;

import com.echelon.model.Queue;

public interface QueueRepository extends CrudRepository<Queue, Long> {

}
