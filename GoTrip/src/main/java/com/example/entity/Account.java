package com.example.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Account {
	/*
	@OneToMany(mappedBy = "account")
	private List<Tour> tours;
	
	public Iterable<Tour> getTours(){
		return tours;
	}
	*/
}
