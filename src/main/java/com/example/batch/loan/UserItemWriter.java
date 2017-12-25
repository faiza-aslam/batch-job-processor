package com.example.batch.loan;

import java.util.ArrayList;
import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.batch.loan.service.BatchService;
import com.example.model.JobTasks;

@Named
public class UserItemWriter extends AbstractItemWriter {

	@Inject
	private JobContext jobContext;
	
	@Inject
	BatchService service;

	@Override
	public void writeItems(List<Object> items) throws Exception {
		
		System.out.println("writeItems: "+ items.size());
		List<JobTasks> users = new ArrayList<>();
		
		items.forEach(item -> {
			System.out.println("Item: "+item.toString());
			users.add((JobTasks)item);
			
		});
		service.saveUsers(users);
		System.out.println("instance id="+jobContext.getInstanceId()+" ----- job id="+jobContext.getExecutionId()+" ---- batch status: "+jobContext.getBatchStatus()+" ----- ExitStatus: "+jobContext.getExitStatus());
	}

}