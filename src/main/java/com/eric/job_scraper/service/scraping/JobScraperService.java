package com.eric.job_scraper.service.scraping;

import com.eric.job_scraper.model.Preference;
import com.eric.job_scraper.service.scraping.url.UrlGenerator;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JobScraperService {
    private final WebDriver webDriver;
    private final Map<String, UrlGenerator> urlGenerators;

    public JobScraperService(WebDriver webDriver, List<UrlGenerator> urlGenerators) {
        this.webDriver = webDriver;
        this.urlGenerators = urlGenerators.stream()
                .collect(Collectors.toMap(
                        generator -> generator.getClass().getSimpleName().replace("UrlGenerator", ""),
                        Function.identity()
                ));
    }

    public Map<String, String> scrapeJobs(Preference preference) {
        Map<String, String> scrapedData = new HashMap<>();

        for (String site : this.urlGenerators.keySet()) {
            UrlGenerator urlGenerator = urlGenerators.get(site);
            String url = urlGenerator.generateUrl(preference);

            webDriver.get(url);
            String data = webDriver.getPageSource();
            scrapedData.put(site, data);
        }
        return scrapedData;
    }
}
