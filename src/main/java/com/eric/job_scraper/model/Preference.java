package com.eric.job_scraper.model;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;

    private String location;

    private boolean remote;

    @ElementCollection // Maps a list of strings to a separate table
    @CollectionTable(name = "preference_keywords", joinColumns = @JoinColumn(name = "preference_id"))
    // Table name and join column
    @Column(name = "keyword") // Column name for the keywords
    private List<String> keywords; // List of keywords

    @ManyToOne // many Preference entities can be associated with one User entity
    @JoinColumn(name = "user_id") // name attribute defines the name of the foreign key column (user_id)
    private User user;

}
