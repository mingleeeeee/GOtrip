package com.example.dao;

import org.springframework.data.repository.CrudRepository;
import com.example.entity.Collection;


public interface CollectionDAO extends CrudRepository<Collection, Long>{
	
}
