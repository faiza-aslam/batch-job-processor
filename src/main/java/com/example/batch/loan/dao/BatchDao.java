package com.example.batch.loan.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.model.Job;
import com.example.model.JobTasks;

public class BatchDao {
	
	/*@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;*/
	
	@PersistenceContext
	private EntityManager em;

	public void save(Job job) {
		/*EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();*/
		em.persist(job);
/*		transaction.commit();
		em.close();*/
	}
	
	public void merge(Job job) {
		/*EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(job);
		transaction.commit();
		em.close();*/
		em.merge(job);
	}
	
	public void saveAll(List<JobTasks> tasks) {
		/*EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();*/
		
		tasks.forEach(task -> {
			em.persist(task);
		});
		
		/*transaction.commit();
		em.close();*/
	}
	
	public Job findById(Integer id) {
		/*EntityManager em = entityManagerFactory.createEntityManager();
		Job job = em.find(Job.class, id);
		em.close();
		return job;*/
		return em.find(Job.class, id);
	}

	public Job findJobByExecutionId(long executionId) {
		/*EntityManager em = entityManagerFactory.createEntityManager();
		Job job = null;
		
		try {
			job = em
					.createNamedQuery("Job.getByExecutionId", Job.class)
					.setParameter("executionId", executionId)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return job;*/
		
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
