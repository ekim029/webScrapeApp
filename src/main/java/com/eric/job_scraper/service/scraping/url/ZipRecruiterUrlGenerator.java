package com.eric.job_scraper.service.scraping.url;

import com.eric.job_scraper.model.Preference;
import org.springframework.stereotype.Component;

@Component
public class ZipRecruiterUrlGenerator implements UrlGenerator {

    @Override
    public String generateUrl(Preference preference) {
        String baseUrl = "https://www.ziprecruiter.com/jobs-search";
        String jobTitle = preference.getJobTitle().replace(" ", "+");
        String location = preference.getLocation().replace(" ", "+");
        return String.format("%s?search=%s&location=%s", baseUrl, jobTitle, location);
    }
}
