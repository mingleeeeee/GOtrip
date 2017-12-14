package com.example.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Hot;
import com.example.entity.Spot;
import com.example.entity.Tour;

public interface HotSpotDAO extends CrudRepository<Hot, Long>{


	
}
