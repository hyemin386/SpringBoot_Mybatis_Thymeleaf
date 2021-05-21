package com.hm.j1.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestSchedule {

	@Scheduled(fixedDelayString = "1000", initialDelayString = "2000")
	public void fixRateScheduleTest() throws Exception {
		System.out.println("fixRateSchedule");
	}
	
	@Scheduled(cron = "* * * * * *")
	public void cronTest() throws Exception {
		System.out.println("Cron");
	}
}
