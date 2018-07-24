package com.zy.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class HelloJob extends QuartzJobBean {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		this.logger.info("Hello,{}", this.name);
	}

}
