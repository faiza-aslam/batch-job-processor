package com.example.batch.loan.service;

import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class JobScheduler {

	private Logger logger = Logger.getLogger(JobScheduler.class.getName());
	
	/**
	 * Job will be executed daily at 10 am in the morning
	 */
	@Schedule(hour="10", minute="0", second="0", timezone="Asia/Karachi")
	public void startJob() {
		
		JobOperator jo = BatchRuntime.getJobOperator();
        Properties props = new Properties();
		props.setProperty("job-uuid", UUID.randomUUID().toString());
        long jid = jo.start("sendNotification", props);
        logger.info("########################## Batch Job Started #############################");
        logger.info("job execution id: "+jid);
	}
	
}
