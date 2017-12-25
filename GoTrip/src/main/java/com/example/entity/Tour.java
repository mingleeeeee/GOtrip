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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tour")
@SessionScope
@Component
public class Tour implements Serializable {

	private static final long serialVersionUID = -645007220877687859L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 1, message="請填入行程名稱")
	@Column(name = "name")
	private String name;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="請選擇出發日期")
	@Column(name = "begin_date")
	private Date beginDate;
	
	@NotNull(message="請填入旅遊天數")
	@Min(value = 1, message = "請填入正確天數")
	@Column(name = "days")
	private Long days;

	@Column(name = "note")
	private String note;

	@ManyToOne
	@JoinColumn(name = "usernametour")
	private Account accountTour;

	@OneToMany(mappedBy = "tour")
	private List<Spot> spots;

	private transient MultipartFile photoFile;
	
	private String photo;

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

	public Long getDays() {
		return days;
	}

	public void setDays(Long days) {
		this.days = days;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Account getAccountTour() {
		return accountTour;
	}

	public void setAccountTour(Account accountTour) {
		this.accountTour = accountTour;
	}

	public Iterable<Spot> getSpots() {
		return spots;
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
	
	public void setPhotoByParam(String newPhoto) {
		this.photo = newPhoto;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
