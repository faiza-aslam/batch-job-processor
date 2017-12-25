package com.example.batch.loan;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.model.JobTasks;

@Named
public class UserItemProcessor implements ItemProcessor {

	@Inject
	private JobContext jobContext;

	@Override
	public JobTasks processItem(Object item) throws Exception {
		
		JobTasks user = (JobTasks) item;
		System.out.println("processItem user --- : " + user.getUserId());
		
		String jobId = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId()).getProperty("job-id");
		user.setJobId(Integer.parseInt(jobId));
		
		//Here any notification can be sent to user
				
		return user;
	}

}
