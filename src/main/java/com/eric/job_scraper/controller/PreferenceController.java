package com.eric.job_scraper.controller;

import com.eric.job_scraper.model.Job;
import com.eric.job_scraper.model.Preference;
import com.eric.job_scraper.service.core.PreferenceService;
import com.eric.job_scraper.service.scraping.JobScraperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
// You use @RestController when your class will handle HTTP requests and return JSON or other data in the response body.

@RequestMapping("/preferences")
// This annotation is used at the class level (or method level) to define the base URL path for your REST API.

public class PreferenceController {

    private final PreferenceService preferenceService;
    private final JobScraperService jobScraperService;

    public PreferenceController(PreferenceService preferenceService, JobScraperService jobScraperService) {
        this.preferenceService = preferenceService;
        this.jobScraperService = jobScraperService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Preference> createPreference(@PathVariable Long userId, @RequestBody Preference preference) {
        return ResponseEntity.ok(preferenceService.createPreference(userId, preference));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Preference>> getPreferencesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(preferenceService.getPreferenceByUserId(userId));
    }

    @PutMapping("/{preferenceId}")
    public ResponseEntity<Preference> updatePreference(@PathVariable Long preferenceId, @RequestBody Preference updatedPreference) {
        return ResponseEntity.ok(preferenceService.updatePreference(preferenceId, updatedPreference));
    }

    @DeleteMapping("/{preferenceId}")
    public ResponseEntity<Void> deletePreference(@PathVariable Long preferenceId) {
        preferenceService.deletePreference(preferenceId);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/user/{userId}/jobs")
    public ResponseEntity<List<Job>> getJobsByUserId(@PathVariable Long userId) {
        Preference preference = preferenceService.getPreferenceByUserId(userId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No preference found for user"));

        Map<String, List<Job>> jobsBySite = jobScraperService.scrapeJobs(preference);
        List<Job> jobs = jobsBySite.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return ResponseEntity.ok(jobs);
    }
}
