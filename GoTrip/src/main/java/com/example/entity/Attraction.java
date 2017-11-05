package com.example.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attraction")
public class Attraction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private int day;
	
	@Column(name = "delay_time")
	private Time delayTime;
	
	@ManyToOne
	@JoinColumn(name = "tour_id")
	private Tour tour;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Time getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Time delayTime) {
		this.delayTime = delayTime;
	}
}
