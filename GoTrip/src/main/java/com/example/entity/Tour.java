package com.example.entity;

import java.io.Serializable;
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

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@Table(name = "tour")
@SessionScope
@Component
public class Tour implements Serializable{

	private static final long serialVersionUID = -645007220877687859L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name = "begin_date")
	private Date beginDate;
	
	@Column(name="days")
	private int days;
	
	@Column(name="note")
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "users_username")
	private Account account;
	
	@OneToMany(mappedBy = "tour")
	private List<Spot> spots;
	
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
	
	public Account getAccount(){
		return account;
	}
	
	public void setAccount(Account account){
		this.account = account;
	}
	
	public Iterable<Spot> getSpots(){
		return spots;
	}
	
}
