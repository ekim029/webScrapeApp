package com.eric.job_scraper.model;

public class Job {
    private String jobTitle;
    private String company;
    private String location;
    private String url;

    public Job(String jobTitle, String company, String location, String url) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.url = url;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getUrl() {
        return url;
    }
}
