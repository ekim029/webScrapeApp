package com.eric.job_scraper.service.scraping.api;

import com.eric.job_scraper.model.Job;
import com.eric.job_scraper.model.Preference;

import java.util.List;

public interface JobSiteApi {
    String getSiteName();
    List<Job> fetchJobs(Preference preference);
}
