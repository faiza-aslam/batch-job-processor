package com.example.batch.loan;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.batch.loan.service.BatchService;
import com.example.model.Job;

@Named
public class CreateJobBachlet extends AbstractBatchlet {

	@Inject
	private JobContext jobContext;
	
	@Inject
	private BatchService service;
	
	@Override
	public String process() throws Exception {
		String jobUuid = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId()).getProperty("job-uuid");
		System.out.println("######################### Create Job Batchlet ....... #########################  uuid: "+jobUuid);
		Job job = new Job(jobContext.getJobName(), jobUuid, jobContext.getExecutionId());
		service.save(job);
		System.out.println("Job ID returned: " + job.getId());
		BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId()).setProperty("job-id", String.valueOf(job.getId()));
		return BatchStatus.COMPLETED.toString();
	}

}
