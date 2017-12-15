package com.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@Table(name = "authorities")
@SessionScope
@Component
public class Authority implements Serializable{
	
	private static final long serialVersionUID = 2716932157551659386L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String authority;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private Account accountAutho;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Account getAccountAutho() {
		return accountAutho;
	}

	public void setAccountAutho(Account accountAutho) {
		this.accountAutho = accountAutho;
	}
	
	
}
