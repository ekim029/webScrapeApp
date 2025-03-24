package com.eric.job_scraper.repository;

import com.eric.job_scraper.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    List<Preference> findUserById(Long userId);
}
