package com.example.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Spot;
import com.example.entity.Tour;

public interface SpotDAO extends CrudRepository<Spot, Long>{
	public Iterable<Spot> findByTourAndDayOrderBySequenceAsc(Tour tour, Integer day);
}
