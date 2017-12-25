package com.example.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = -2025504813051006301L;
	
	protected Integer id;
	protected Date createdTimestamp;
	protected Date updatedTimestamp;
	protected Boolean isActive=true;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Basic
	@Column(name = "created_timestamp")
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Basic
	@Column(name = "updated_timestamp")
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}
	@Basic
	@Column(name = "is_active")
	public Boolean isActive() {
		return isActive;
	}
	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@PrePersist
	void onCreate() {
		setCreatedTimestamp(new Date());
	}
	
	@PreUpdate
	void onUpdate() {
		setUpdatedTimestamp(new Date());
	}

}
