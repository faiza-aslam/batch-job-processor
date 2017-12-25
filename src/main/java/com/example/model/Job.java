package com.example.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Job.getByExecutionId", query="select j from Job j where j.executionId=(:executionId)")
})
@Table(name="job")
public class Job extends BaseModel {

	private static final long serialVersionUID = -1494982416244225396L;
	
	private String name;
	private boolean completed=false;
	private Date completionTimestamp;
	private String jobUuid;
	private long executionId;

	public Job() { }
	
	public Job(String name, String jobUuid, long executionId) {
		super();
		this.name = name;
		this.jobUuid = jobUuid;
		this.executionId = executionId;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Basic
	@Column(name = "completed")
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	@Basic
	@Column(name = "completion_timestamp")
	public Date getCompletionTimestamp() {
		return completionTimestamp;
	}
	public void setCompletionTimestamp(Date completionTimestamp) {
		this.completionTimestamp = completionTimestamp;
	}
	@Basic
	@Column(name = "job_uuid")
	public String getJobUuid() {
		return jobUuid;
	}
	public void setJobUuid(String jobUuid) {
		this.jobUuid = jobUuid;
	}
	@Basic
	@Column(name = "execution_id")
	public long getExecutionId() {
		return executionId;
	}
	public void setExecutionId(long executionId) {
		this.executionId = executionId;
	}
}
