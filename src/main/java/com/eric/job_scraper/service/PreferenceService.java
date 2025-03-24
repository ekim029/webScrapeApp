package com.eric.job_scraper.service;

import com.eric.job_scraper.model.Preference;
import com.eric.job_scraper.model.User;
import com.eric.job_scraper.repository.PreferenceRepository;
import com.eric.job_scraper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Used to mark a class as a service component in the Spring framework.
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;
    private final UserRepository userRepository;

    public PreferenceService(PreferenceRepository preferenceRepository, UserRepository userRepository) {
        this.preferenceRepository = preferenceRepository;
        this.userRepository = userRepository;
    }

    public Preference createPreference(Long userId, Preference preference) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        preference.setUser(user);
        return preferenceRepository.save(preference);
    }

    public List<Preference> getPreferenceByUserId(Long userId) {
        return preferenceRepository.findByUserId(userId);
    }

    public Preference updatePreference(Long preferenceId, Preference newPreference) {
        Preference preference = preferenceRepository.findById(preferenceId)
                .orElseThrow(() -> new RuntimeException("No preference found"));

        preference.setJobTitle(newPreference.getJobTitle());
        preference.setLocation(newPreference.getLocation());
        preference.setRemote(newPreference.isRemote());
        preference.setKeywords(newPreference.getKeywords());

        return preferenceRepository.save(preference);
    }

    public void deletePreference(Long preferenceId) {
        if (!preferenceRepository.existsById((preferenceId))) {
            throw new RuntimeException("Preference not found");
        }
        preferenceRepository.deleteById(preferenceId);
    }

}
