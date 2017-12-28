package com.example.batch.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@Table(name="user")
public class User extends BaseModel {
	
	private static final long serialVersionUID = -4609050986243669593L;

	private String email;	
	private String fullName;	
	private String mobileNumber;
	private List<UserTask> userTasks;

	public User() {
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="full_name")
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name="mobile_number")
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="user")
	public List<UserTask> getUserTasks() {
		return this.userTasks;
	}

	public void setUserTasks(List<UserTask> userTasks) {
		this.userTasks = userTasks;
	}

	/*public UserTask addUserTask(UserTask userTask) {
		getUserTasks().add(userTask);
		userTask.setUser(this);

		return userTask;
	}

	public UserTask removeUserTask(UserTask userTask) {
		getUserTasks().remove(userTask);
		userTask.setUser(null);

		return userTask;
	}*/

}