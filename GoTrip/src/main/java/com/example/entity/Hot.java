package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "hot")
@SessionScope
@Component
public class Hot implements Serializable{

	private static final long serialVersionUID = -6721441658405233765L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="name")	
	@Size(min=1, message="不能是空白")
	private String name;
		
	private transient MultipartFile photoFile;
	@NotNull
	@Size(min=1,message="不能是空白")
	private String photo;
	
	@Column(name="description")
	@NotNull
	@Size(min=1,message="不能是空白")
	private String description;

	@NotNull
	@Size(min=1,message="不能是空白")
	@Column(name="address")
	private String address;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(MultipartFile photoFile) {
		this.photoFile = photoFile;
	}

	public void setPhoto() {
		this.photo = photoFile.getOriginalFilename();
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
