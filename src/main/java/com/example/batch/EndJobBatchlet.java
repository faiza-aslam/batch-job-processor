package com.example.batch;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class EndJobBatchlet extends AbstractBatchlet {
	
	private static final Logger logger = LoggerFactory.getLogger(EndJobBatchlet.class);
	
	@Override
	public String process() throws Exception {
		logger.info("End Job Batchlet ....... ");
		return BatchStatus.COMPLETED.toString();
	}

}
