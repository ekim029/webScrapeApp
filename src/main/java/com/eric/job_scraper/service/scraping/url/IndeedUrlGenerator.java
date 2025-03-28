package com.eric.job_scraper.service.scraping.url;

import com.eric.job_scraper.model.Preference;
import org.springframework.stereotype.Component;

@Component
public class IndeedUrlGenerator implements UrlGenerator{

    @Override
    public String generateUrl(Preference preference) {
        String baseUrl = "https://www.indeed.com/jobs";
        String jobTitle = preference.getJobTitle().replace(" ", "+");
        String location = preference.getLocation().replace(" ", "+");
        return String.format("%s?q=%s&l=%s", baseUrl, jobTitle, location);
    }

}
