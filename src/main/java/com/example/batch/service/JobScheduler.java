package com.example.batch.service;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class JobScheduler {

	private Logger logger = LoggerFactory.getLogger(JobScheduler.class);
	
	/**
	 * Job will be executed daily at 10 am in the morning
	 */
	@Schedule(hour="10", minute="0", second="0", timezone="Asia/Karachi")
//	@PostConstruct
	public void startJob() {
		
		JobOperator jo = BatchRuntime.getJobOperator();
        Properties props = new Properties();
        long jid = jo.start("sendNotification", props);
        logger.info("Batch Job Started .. Job execution id: {}", jid);
	}
	
}
