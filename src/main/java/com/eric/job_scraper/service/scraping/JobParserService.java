package com.eric.job_scraper.service.scraping;

import com.eric.job_scraper.model.Job;
import com.eric.job_scraper.model.Preference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;
import java.util.stream.Collectors;

@Service
public class JobParserService {

    public Map<String, List<Job>> parseJob(Map<String, String> scrapedData, Preference preference) {
        Map<String, List<Job>> jobsBySite = new HashMap<>();

        List<String> desiredKeywords = preference.getDesiredKeywords();
        Pattern desiredPattern;
        if (desiredKeywords == null || desiredKeywords.isEmpty()) {
            desiredPattern = Pattern.compile(".*");
        } else {
            String stringRegex = desiredKeywords.stream()
                    .map(keyword -> Pattern.quote(keyword))
                    .collect(Collectors.joining("|"));

            desiredPattern = Pattern.compile(stringRegex);
        }

        List<String> excludedKeywords = preference.getExcludedKeywords();
        Pattern excludedPattern;
        if (excludedKeywords == null || excludedKeywords.isEmpty()) {
            excludedPattern = Pattern.compile("$^");
        } else {
            String stringRegex = excludedKeywords.stream()
                    .map(keyword -> Pattern.quote(keyword))
                    .collect(Collectors.joining("|"));

            excludedPattern = Pattern.compile(stringRegex);
        }

        for (String site : scrapedData.keySet()) {
            String data = scrapedData.get(site);

            List<Job> jobs = new ArrayList<>();
            if (site.equals("Indeed")) {
                jobs = parseIndeedJobs(data, desiredPattern, excludedPattern);
            } else if (site.equals("ZipRecruiter")) {
                jobs = parseZipRecruiterJobs(data, desiredPattern, excludedPattern);
            }
            jobsBySite.put(site, jobs);
        }
        return jobsBySite;
    }

    public List<Job> parseIndeedJobs(String data, Pattern desiredPattern, Pattern excludedPattern) {

    }

    public List<Job> parseZipRecruiterJobs(String data, Pattern desiredPattern, Pattern excludedPattern) {

    }

}
