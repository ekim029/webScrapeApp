package com.eric.job_scraper.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfig {

    @Bean
    public WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        return new  ChromeDriver();
    }
}
