package com.example.batch.loan;

import java.util.Date;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.batch.loan.service.BatchService;
import com.example.model.Job;

@Named
public class UpdateJobBachlet extends AbstractBatchlet {
	
	@Inject
	private JobContext jobContext;
	
	@Inject
	private BatchService service;
	
	@Override
	public String process() throws Exception {
		System.out.println("Update Batch Job status to completed ....... ");
		int jobId = Integer.parseInt(BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId()).getProperty("job-id"));
		Job job = service.findById(jobId);
		job.setCompleted(true);
		job.setCompletionTimestamp(new Date());
		service.updateJob(job);
		return BatchStatus.COMPLETED.toString();
	}

}
