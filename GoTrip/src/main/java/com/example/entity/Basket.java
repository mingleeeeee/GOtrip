package com.example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class Basket implements Serializable{
	
	private static final long serialVersionUID = 8345226702719403632L;
	private List<Spot> spots;

	public Iterable<Spot> getSpots() {
		return spots;
	}

	public void setSpots(List<Spot> spots) {
		this.spots = spots;
	}
	
	public void add(Spot spot){
		spots.add(spot);
	}
	
	public void cleanup(){
		spots = new ArrayList<Spot>();
	}
	
	
}
