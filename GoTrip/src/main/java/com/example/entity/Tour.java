package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tour")
public class Tour {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@Column(name = "begin_date")
	private Date beginDate;
	private int days;
	private String note;
	
	/*
	@ManyToOne
	@JoinColumn(name = "username")
	private Account account;
	*/
	
	@OneToMany(mappedBy = "tour")
	private List<Attraction> attractions;
	
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
	
	public Date getBeginDate() {
		return beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public int getDays() {
		return days;
	}
	
	public void setDays(int days) {
		this.days = days;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	/*
	public Account getAccount(){
		return account;
	}
	
	public void setAccount(Account account){
		this.account = account;
	}
	 */
	
	public Iterable<Attraction> getAttractions(){
		return attractions;
	}
	
}
