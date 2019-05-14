package com.prediction.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("matchScheduler")
public class MatchSchedulerImpl implements MatchScheduler {
	private static final Logger LOG = LoggerFactory.getLogger(MatchSchedulerImpl.class);
	@Override
	/* 
	 * 1st argument - second
	 * 2nd argument - minute
	 * 3rd argument - hour in 24 hours format
	 * 4th argument - day of month
	 * 5th argument - month
	 * 6th argument -  day(s) of week
	 */
	@Scheduled(cron="0 0 1 * * *", zone="Asia/Calcutta")
	public void scheduleMatch() {
		LOG.info("Event created!");
	}
}
