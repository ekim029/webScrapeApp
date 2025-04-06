package com.eric.job_scraper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private String jobTitle;
    private String company;
    private String location;
    private String url;
    private String description;
}
