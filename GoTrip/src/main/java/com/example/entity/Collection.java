package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "collection")
@SessionScope
@Component
public class Collection implements Serializable{
	
	private static final long serialVersionUID = -3918455050327236720L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="place_id")
	private String placeId;
	
	@Column(name = "address")
	private String address;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usernamecol")
	private Account accountCol;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}

	public Account getAccountCol() {
		return accountCol;
	}

	public void setAccountCol(Account accountCol) {
		this.accountCol = accountCol;
	}

}
