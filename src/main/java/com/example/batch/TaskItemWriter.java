package com.example.batch;

import java.util.ArrayList;
import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.batch.service.BatchService;
import com.example.model.UserTask;

@Named
public class TaskItemWriter extends AbstractItemWriter {

	private static final Logger logger = LoggerFactory.getLogger(TaskItemWriter.class);
	
	@Inject
	BatchService service;

	@Override
	public void writeItems(List<Object> items) throws Exception {
		
		logger.info("writeItems size: {}", items.size());
		List<UserTask> tasks = new ArrayList<>();
		
		items.forEach(item -> {
			tasks.add((UserTask)item);
			
		});
		service.updateUserTasks(tasks);
	}

}
