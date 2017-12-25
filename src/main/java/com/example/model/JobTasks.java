package com.example.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="job_tasks")
public class JobTasks extends BaseModel {

	private static final long serialVersionUID = -6338875119036703262L;
	
	private Integer jobId;
	private Integer userId;
	private Integer tenure;
	private Date dueDate;
	private Double amountToRepay;

	@Basic
	@Column(name="job_id")
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	@Basic
	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Basic
	@Column(name="tenure")
	public Integer getTenure() {
		return tenure;
	}
	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}
	@Basic
	@Column(name="due_date")
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	@Basic
	@Column(name="amount_to_repay")
	public Double getAmountToRepay() {
		return amountToRepay;
	}
	public void setAmountToRepay(Double amountToRepay) {
		this.amountToRepay = amountToRepay;
	}	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		JobTasks that = (JobTasks) obj;
		
		if(userId!=null ? userId!=that.userId : that.userId!=null) return false;
		
		return true;
	}
}
