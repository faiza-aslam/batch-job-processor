package com.example.batch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="UserTask.findAll", query="SELECT u FROM UserTask u"),
	@NamedQuery(name="UserTask.getOverDueTasks", query="SELECT t FROM UserTask t join fetch t.user where t.dueDate < current_timestamp and t.active=true and t.userNotified=false order by t.createdTimestamp desc")
})
@Table(name="user_task")
public class UserTask extends BaseModel {

	private static final long serialVersionUID = 3126230758140521672L;

	private String name;	
	private Date dueDate;
	private String priority;
	private Boolean userNotified;
	private User user;

	public UserTask() {
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="due_date")
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="priority")
	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Column(name="user_notified")
	public Boolean getUserNotified() {
		return this.userNotified;
	}

	public void setUserNotified(Boolean userNotified) {
		this.userNotified = userNotified;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}