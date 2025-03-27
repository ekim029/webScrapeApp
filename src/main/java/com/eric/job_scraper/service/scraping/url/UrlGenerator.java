package com.eric.job_scraper.service.scraping.url;

import com.eric.job_scraper.model.Preference;

public interface UrlGenerator {
    String generateUrl(Preference preference);
}
