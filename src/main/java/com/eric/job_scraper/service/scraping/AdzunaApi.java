package com.eric.job_scraper.service.scraping;

import com.eric.job_scraper.model.Job;
import com.eric.job_scraper.model.Preference;
import com.eric.job_scraper.service.scraping.api.JobSiteApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    }
}
