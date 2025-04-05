package com.eric.job_scraper.service.scraping;

import com.eric.job_scraper.model.Job;
import com.eric.job_scraper.model.Preference;
import com.eric.job_scraper.service.scraping.api.JobSiteApi;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobScraperService {
    private final List<JobSiteApi> jobSiteApis;

    public JobScraperService(List<JobSiteApi> jobSiteApis) {
        this.jobSiteApis = jobSiteApis;
    }

    public Map<String, List<Job>> scrapeJobs(Preference preference) {
        Map<String, List<Job>> jobsBySite = new HashMap<>();
        for (JobSiteApi jobSiteApi : jobSiteApis) {
            List<Job> jobs = jobSiteApi.fetchJobs(preference);
            jobsBySite.put(jobSiteApi.getSiteName(), jobs);
        }
        return jobsBySite;
    }
}
