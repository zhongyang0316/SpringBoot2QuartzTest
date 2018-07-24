package com.zy.quartz.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zy.quartz.job.HelloJob;

@Configuration
public class QuartzConfig {
	
	@Bean
	public JobDetail helloJobDetail() {
		return JobBuilder.newJob(HelloJob.class).withIdentity("helloJob")
				.usingJobData("name", "World").storeDurably().build();
	}
	
	@Bean
	public Trigger helloJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(2).repeatForever();

		return TriggerBuilder.newTrigger().forJob(helloJobDetail())
				.withIdentity("helloTrigger").withSchedule(scheduleBuilder).build();
	}

}
