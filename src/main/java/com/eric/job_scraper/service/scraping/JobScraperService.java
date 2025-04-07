package com.eric.job_scraper.service.scraping;

import com.eric.job_scraper.model.Job;
import com.eric.job_scraper.model.Preference;
import com.eric.job_scraper.service.scraping.api.JobSiteApi;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JobScraperService {
    private final List<JobSiteApi> jobSiteApis;

    public JobScraperService(List<JobSiteApi> jobSiteApis) {
        this.jobSiteApis = jobSiteApis;
    }

    public Map<String, List<Job>> scrapeJobs(Preference preference) {
        Map<String, List<Job>> jobsBySite = new HashMap<>();
        List<String> desiredKeywords = preference.getDesiredKeywords();
        List<String> excludedKeywords = preference.getExcludedKeywords();
        for (JobSiteApi jobSiteApi : jobSiteApis) {
            List<Job> jobs = jobSiteApi.fetchJobs(preference);
            List<Job> filteredJobs = jobs.stream()
                            .filter(job -> {
                                String description = job.getDescription().toLowerCase();
                                boolean containsDesired = desiredKeywords == null || desiredKeywords.isEmpty() ||
                                        desiredKeywords.stream().anyMatch(keyword -> description.contains(keyword.toLowerCase()));

                                boolean containsExcluded = excludedKeywords != null &&
                                        excludedKeywords.stream().anyMatch(keyword -> description.contains(keyword.toLowerCase()));

                                return containsDesired && !containsExcluded;
                            })
                                    .collect(Collectors.toList());
            jobsBySite.put(jobSiteApi.getSiteName(), filteredJobs);
        }
        return jobsBySite;
    }
}
