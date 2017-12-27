package com.example.batch;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.batch.service.BatchService;
import com.example.model.UserTask;

@Named
public class TaskItemReader extends AbstractItemReader {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskItemReader.class);
	
	private Iterator<UserTask> iterator = null;
	private UserTask lastElement;
	
	@Inject
	private BatchService service;
	
	@Override
	public void open(Serializable checkpoint) throws Exception {
		super.open(checkpoint);
		logger.info("Opening Reader ..... checkpoint: {}", checkpoint);

		List<UserTask> users = service.getUserTasks();
		iterator = users.iterator();
		
		if(checkpoint != null) {
			UserTask checkpointUser = (UserTask) checkpoint;
			logger.info("Checkpoint Task Found.. Task ID: {}", checkpointUser.getId());
			while(iterator.hasNext() && !iterator.next().getId().equals(checkpointUser.getId())) {
				logger.info("skipping already read tasks ... ");
			}
		}
	}
	
	@Override
	public Object readItem() throws Exception {
		
		UserTask task = null;
		
		if(iterator.hasNext()) {
			task = iterator.next();
			if(task!=null) {
				logger.info("Read Item - Task ID: {}", task.getId());
				lastElement = task;
			}
		}
		
		return task;
	}
	
	@Override
	public Serializable checkpointInfo() throws Exception {
		logger.info("Returning checkpoint Info: {} ", lastElement.getId());
		return lastElement;
	}

}
