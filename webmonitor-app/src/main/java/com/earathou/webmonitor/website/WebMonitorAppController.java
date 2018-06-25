package com.earathou.webmonitor.website;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.earathou.webmonitor.email.service.EmailService;
import com.earathou.webmonitor.ping.service.PingWebService;
import com.earathou.webmonitor.website.service.WebsiteService;

@Controller
public class WebMonitorAppController {
	
	@Autowired
	private WebsiteService websiteservice;
	
	@Autowired
	private PingWebService pingWebsiteservice;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping(value = "/")
	public ModelAndView displayWebsites() {
		ArrayList<WebsiteForm> websites = websiteservice.getAllWebsites();
		
		boolean hasWebsiteNotAvailable = false;
		
		for (WebsiteForm website : websites) {
			String status = pingWebsiteservice.url(website.getUrl());
			website.setStatus(status);
			
			if (!hasWebsiteNotAvailable && PingWebService.WEBPAGE_STATUS_NOT_AVAILABLE.equals(status))
				hasWebsiteNotAvailable = true;
		}
		
		if (hasWebsiteNotAvailable) {
			emailService.setWebsites(websites);
			new Thread(emailService).start();
		}
		
		WebsiteListForm websiteListForm = new WebsiteListForm();
		websiteListForm.setWebsites(websites);
		
		return new ModelAndView("main", "websiteListForm", websiteListForm);
	}
	
	@GetMapping(value = "/addweburlpage")
	public String displayAddURLpage(WebsiteForm websiteForm) {
		return "addweburl";
	}
	
	@PostMapping(value = "/addwebsite")
	public ModelAndView addNewWebsiteUrl(@Valid @ModelAttribute("websiteForm") WebsiteForm websiteForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("addweburl");
		}
		
		websiteservice.addWebsite(websiteForm);
		System.out.println("Website added -> "  + websiteForm);
		return new ModelAndView("successful", "websiteForm", websiteForm);
	}
}
