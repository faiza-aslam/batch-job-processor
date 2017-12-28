package com.example.batch.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.batch.model.UserTask;

public class BatchDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void updateAll(List<UserTask> tasks) {
		tasks.forEach(task -> {
			em.merge(task);
		});
	}
	
	public List<UserTask> getOverDueUserTasks() {	
		
		return em
				.createNamedQuery("UserTask.getOverDueTasks", UserTask.class)
				.getResultList();
		
	}
}
