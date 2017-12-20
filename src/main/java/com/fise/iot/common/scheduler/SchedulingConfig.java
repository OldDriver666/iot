package com.fise.iot.common.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {
	
	  @Scheduled(cron = "0/5 * * * * ?") // 每5秒执行一次
	    public void scheduler() {
	        System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
	    }

}
