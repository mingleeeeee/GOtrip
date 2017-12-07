package com.example.dao;

import org.springframework.data.repository.CrudRepository;
import com.example.entity.Tour;

public interface TourDAO extends CrudRepository<Tour, Long>{
	
}
