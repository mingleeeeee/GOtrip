package com.example.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@Table(name = "users")
@SessionScope
@Component
public class Account implements Serializable{
	
	private static final long serialVersionUID = 6813281572200532922L;

	@NotNull
	@Size(min=1)
	@Id
	@Column(name="username")
	private String username;
	
	@NotNull
	@Size(min=1)
	@Column(name="password")
	private String password;
	

	@Column(name="enabled")
	private boolean enabled;
	
	@NotNull
	@Size(min=1)
	@Column(name="name")
	private String name;
	
	@NotNull
	@Size(min=1)
	@Column(name="email")
	private String email;
	
	@NotNull
	@Size(min=1)
	@Column(name="phone")
	private String phone;
	
	@OneToMany(mappedBy = "account")
	private List<Tour> tours;
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Iterable<Tour> getTours(){
		return tours;
	}
	
	
}
