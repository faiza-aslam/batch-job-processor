package com.example.batch.loan.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.model.Job;
import com.example.model.JobTasks;

public class BatchDao {
	
	@PersistenceContext
	private EntityManager em;

	public void save(Job job) {
		em.persist(job);
	}
	
	public void merge(Job job) {
		em.merge(job);
	}
	
	public void saveAll(List<JobTasks> tasks) {
		tasks.forEach(task -> {
			em.persist(task);
		});
	}
	
	public Job findById(Integer id) {
		return em.find(Job.class, id);
	}

	public Job findJobByExecutionId(long executionId) {	
		
		Job job = null;
		
		try {
			job = em
					.createNamedQuery("Job.getByExecutionId", Job.class)
					.setParameter("executionId", executionId)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return job;
	}
}
