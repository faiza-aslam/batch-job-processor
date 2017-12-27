package com.example.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.batch.service.MailService;
import com.example.model.UserTask;

@Named
public class TaskItemProcessor implements ItemProcessor {

	private final Logger logger = LoggerFactory.getLogger(TaskItemProcessor.class);
	
	@Inject
	private MailService mailService;

	@Override
	public UserTask processItem(Object item) throws Exception {
		
		UserTask task = (UserTask) item;
		logger.info("processItem task --- : {} ", task.getId());
		
		//Here any notification can be sent to user
		mailService.sendEmail(task.getUser().getEmail(), task.getUser().getFullName(), task.getName());
		
		task.setUserNotified(true);
		return task;
	}

}
