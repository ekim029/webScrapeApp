package com.eric.job_scraper.controller;

import com.eric.job_scraper.model.Preference;
import com.eric.job_scraper.service.core.PreferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
// You use @RestController when your class will handle HTTP requests and return JSON or other data in the response body.

@RequestMapping("/preferences")
// This annotation is used at the class level (or method level) to define the base URL path for your REST API.

public class PreferenceController {

    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
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

}
