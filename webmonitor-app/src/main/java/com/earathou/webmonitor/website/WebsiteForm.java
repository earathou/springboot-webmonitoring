package com.earathou.webmonitor.website;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

public class WebsiteForm {
	
	@NotNull
    @Size(min=1, max=255, message = "Please enter between {min} and {max} characters.")
	private String name;
	
	@URL
	@NotNull
    @Size(min=5, max=100, message = "Please enter between {min} and {max} characters.")
	private String url;
	
	private String status;
	
	public WebsiteForm(){
		
	}
	
	public WebsiteForm(String url){
		this.url = url;
	}
	
	public WebsiteForm(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return this.name + ": " + this.url;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
