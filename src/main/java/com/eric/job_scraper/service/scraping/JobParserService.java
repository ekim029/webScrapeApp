package com.eric.job_scraper.service.scraping;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobParserService {

    public Map<String, List<String>> parseJob(Map<String, String> scrapedData) {
        Map<String, List<String>> jobsBySite = new HashMap<>();
        for (String site : scrapedData.keySet()) {
            String data = scrapedData.get(site);

            List<String> jobs = new ArrayList<>();
            if (site.equals("Indeed")) {
                jobs = parseIndeedJobs(data);
            } else if (site.equals("ZipRecruiter")) {
                jobs = parseZipRecruiterJobs(data);
            }
            jobsBySite.put(site, jobs);
        }
        return jobsBySite;
    }

    public List<String> parseIndeedJobs(String data) {

    }

    public List<String> parseZipRecruiterJobs(String data) {

    }

}
