package com.example.batch.loan.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.example.batch.loan.dao.BatchDao;
import com.example.model.Job;
import com.example.model.JobTasks;

@Stateless
public class BatchService {
	
	@Resource(lookup = "java:jboss/jdbc/tez-ds")
	private DataSource dataSource;
	
	@Inject
	private BatchDao dao;
	
	public void save(Job job) {
		dao.save(job);
	} 
	
	public void saveUsers(List<JobTasks> users) {
		dao.saveAll(users);
	}
	
	public List<JobTasks> getUsers() {
		
		QueryRunner qr = new QueryRunner(dataSource);
		List<JobTasks> users = new ArrayList<>();
		String sql = "call tez_advance_delinquency_proc()";
		
		try {
			users = (List<JobTasks>) qr.query(sql, new BeanListHandler<JobTasks>(JobTasks.class));
		} catch (SQLException e) { 
			System.out.println("Unable to call stored procedure .... message: "+e.getMessage());
			e.printStackTrace();
		}
		System.out.println("users returned: "+users.size());
		return users; 
	}
	
	public Job findById(int id) {
		return dao.findById(id);
	}
	
	public void updateJob(Job job) {
		dao.merge(job);
	}
	
	public Job findJobByExecutionId(long executionId) {
		return dao.findJobByExecutionId(executionId);
	}
	
}
