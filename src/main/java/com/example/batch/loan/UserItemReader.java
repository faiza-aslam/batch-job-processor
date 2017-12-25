package com.example.batch.loan;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.batch.loan.service.BatchService;
import com.example.model.JobTasks;

@Named
public class UserItemReader extends AbstractItemReader {
	
	private Iterator<JobTasks> iterator = null;
	private JobTasks lastElement;
	
	@Inject
	private BatchService service;
	
	@Override
	public void open(Serializable checkpoint) throws Exception {
		super.open(checkpoint);
		System.out.println("Opening Reader ..... checkpoint: " + checkpoint);

		List<JobTasks> users = service.getUsers();
		iterator = users.iterator();
		
		if(checkpoint != null) {
			JobTasks checkpointUser = (JobTasks) checkpoint;
			System.out.println("Checkpoint Found: " + checkpointUser.getUserId());
			while(iterator.hasNext() && !iterator.next().getUserId().equals(checkpointUser.getUserId())) {
				System.out.println("skipping already read users ... ");
			}
		}
	}
	
	@Override
	public Object readItem() throws Exception {
		
		JobTasks user=null;
		
		if(iterator.hasNext()) {
			user = iterator.next();
			System.out.println("Read Item - UserId: "+user.getUserId());
			lastElement = user;
		}
		
		return user;
	}
	
	@Override
	public Serializable checkpointInfo() throws Exception {
		System.out.println("Returning checkpoint Info ---------- " + lastElement.getUserId());
		return lastElement;
	}

}
