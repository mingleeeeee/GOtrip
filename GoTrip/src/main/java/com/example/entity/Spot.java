package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "spot")
public class Spot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="placeId")
	private String placeId;
	
	@Column(name="on_which_day")
	private int on_which_day;
	
	@Column(name = "duration")
	private String duration;
	
	@Column(name="note")
	private String note;
	
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

	public int getOn_which_day() {
		return on_which_day;
	}

	public void setOn_which_day(int on_which_day) {
		this.on_which_day = on_which_day;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getPlaceId(){
		return placeId;
	}
	
	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}
}
