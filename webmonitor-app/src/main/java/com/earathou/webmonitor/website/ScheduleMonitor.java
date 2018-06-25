package com.earathou.webmonitor.website;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.earathou.webmonitor.email.service.EmailService;
import com.earathou.webmonitor.ping.service.PingWebService;
import com.earathou.webmonitor.website.service.WebsiteService;

@Component
public class ScheduleMonitor {

	@Autowired
	private WebsiteService websiteService;
	
	@Autowired
	private PingWebService pingWebsiteservice;
	
	@Autowired
	private EmailService emailService;

	private static final Logger log = LoggerFactory.getLogger(ScheduleMonitor.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 10000)
	public void reportCurrentTime() {

		boolean hasWebsiteNotAvailable = false;
		ArrayList<WebsiteForm> websites = websiteService.getAllWebsites(); 
		
		for (WebsiteForm website : websiteService.getAllWebsites()) {
			String status = pingWebsiteservice.url(website.getUrl());
			website.setStatus(status);
			
			if (!hasWebsiteNotAvailable && PingWebService.WEBPAGE_STATUS_NOT_AVAILABLE.equals(status)) {
				hasWebsiteNotAvailable = true;
			}
		}

		if (hasWebsiteNotAvailable) {
			emailService.setWebsites(websites);
			emailService.sendMessage();
		}
		
		log.info("The time is now {}, website count: {}", dateFormat.format(new Date()), websites.size());
	}

}
