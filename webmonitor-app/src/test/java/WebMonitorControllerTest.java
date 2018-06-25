import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.earathou.webmonitor.ping.service.PingWebService;
import com.earathou.webmonitor.website.service.WebsiteService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class WebMonitorControllerTest{

	@Autowired
	private WebsiteService websiteservice;

	@Autowired
	private PingWebService pingWebsiteservice;

	@Test
	public void test_runningWebsites() {

		String facebookSite = "http://www.facebook.com";
		websiteservice.addWebsite(facebookSite);

		String googleSite = "http://www.google.com";
		websiteservice.addWebsite(googleSite);

		Assert.assertEquals(PingWebService.WEBPAGE_STATUS_OK, pingWebsiteservice.url(facebookSite));
		Assert.assertEquals(PingWebService.WEBPAGE_STATUS_OK, pingWebsiteservice.url(googleSite));
	}
	
	@Test
	public void test_unavailableWebsites() {

		String facebookSiteXXX = "http://www.faceXXXbook.XXcom";
		websiteservice.addWebsite(facebookSiteXXX);

		String googleSiteXXX = "http://www.googXXXXle.cXXom";
		websiteservice.addWebsite(googleSiteXXX);

		Assert.assertEquals(PingWebService.WEBPAGE_STATUS_NOT_AVAILABLE, pingWebsiteservice.url(facebookSiteXXX));
		Assert.assertEquals(PingWebService.WEBPAGE_STATUS_NOT_AVAILABLE, pingWebsiteservice.url(googleSiteXXX));
	}

}

@Configuration
@ComponentScan("com.earathou.webmonitor")
class AppConfig {
}
