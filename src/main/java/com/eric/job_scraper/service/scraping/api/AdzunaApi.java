package com.eric.job_scraper.service.scraping.api;

import com.eric.job_scraper.model.Job;
import com.eric.job_scraper.model.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AdzunaApi implements JobSiteApi {
    private final RestTemplate restTemplate;
    private static final String ADZUNA_API_BASE_URL = "https://api.adzuna.com/v1/api/jobs/us/search/1";
    private final String ADZUNA_ID;
    private final String ADZUNA_KEY;

    public AdzunaApi(RestTemplate restTemplate, @Value("${ADZUNA_ID}") String ADZUNA_ID, @Value("${ADZUNA_KEY}") String ADZUNA_KEY) {
        this.restTemplate = restTemplate;
        this.ADZUNA_ID = ADZUNA_ID;
        this.ADZUNA_KEY = ADZUNA_KEY;
    }

    @Override
    public String getSiteName() {
        return "Adzuna";
    }

    @Override
    public List<Job> fetchJobs(Preference preference) {
        List<Job> jobs = new ArrayList<>();

        String jobTitle = preference.getJobTitle().replace(" ","%20");
        String location = preference.getLocation().replace(" ", "%20");
        String url = String.format("%s?app_id=%s&app_key=%s&what=%s&where=%s",
                ADZUNA_API_BASE_URL, ADZUNA_ID, ADZUNA_KEY, jobTitle, location);

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> responseBody = response.getBody();

        if (responseBody != null && responseBody.containsKey("results")) {
             List<Map<String, Object>> results = (List<Map<String, Object>>)responseBody.get("results");
             for (Map<String, Object> result : results) {
                 String title = (String) result.get("title");
                 String company = (String) ((Map<String, Object>) result.get("company")).get("display_name");
                 String jobLocation = (String) ((Map<String, Object>) result.get("location")).get("display_name");
                 String urlLink = (String) result.get("redirect_url");
                 String description = (String) result.get("description");
                 description = description.replaceAll("<[^>]+>", "");

                 Job job = new Job(title, company, jobLocation, urlLink, description);
                 jobs.add(job);
             }
        }
        return jobs;
    }
}
