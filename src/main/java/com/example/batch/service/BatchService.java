package com.example.batch.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.batch.dao.BatchDao;
import com.example.model.UserTask;

@Stateless
public class BatchService {
	
	@Inject
	private BatchDao dao;
	
	public List<UserTask> getUserTasks() {
		return dao.getOverDueUserTasks();
	}
	
    public void updateUserTasks(List<UserTask> tasks) {
        dao.updateAll(tasks);
    }

	
}
