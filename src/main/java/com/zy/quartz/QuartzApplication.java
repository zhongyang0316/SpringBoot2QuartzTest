package com.zy.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.zy.quartz"})
public class QuartzApplication {
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = SpringApplication.run(QuartzApplication.class, args);
		System.out.println(ctx.getId() + " started!");
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(QuartzApplication.class);
	}

}
