package com.earathou.webmonitor.website.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.earathou.webmonitor.website.WebsiteForm;

@Service
public class WebsiteService {
	
	private ArrayList<WebsiteForm> websites = new ArrayList<WebsiteForm>();
	
	public ArrayList<WebsiteForm> getAllWebsites() {
		return websites;
	}
	
	public WebsiteForm getWebsite(String id) {
		return websites.stream().filter(t -> t.getUrl().equals(id)).findFirst().get();
	}

	public void addWebsite(WebsiteForm website) {
		websites.add(website);
	}
	
	public void addWebsite(String websiteUrl) {
		websites.add(new WebsiteForm(websiteUrl));
	}
	
	public void addWebsite(String name, String websiteUrl) {
		websites.add(new WebsiteForm(name, websiteUrl));
	}

	public void updateWebsite(String id, WebsiteForm website) {
		for (int i=0; i < websites.size(); i++) {
			WebsiteForm w = websites.get(i);
			
			if (w.getUrl().equals(id)) {
				websites.set(i, website);
				return;
			}
		}
	}

	public void deleteByUrl(String url) {
		websites.removeIf(t -> t.getUrl().equals(url));
	}

}
