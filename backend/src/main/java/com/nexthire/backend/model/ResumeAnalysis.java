package com.nexthire.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private int atsScore;

    private String strengths;

    private String suggestions;


    public ResumeAnalysis() {
    }


    public ResumeAnalysis(String fileName,
                          int atsScore,
                          String strengths,
                          String suggestions) {

        this.fileName = fileName;
        this.atsScore = atsScore;
        this.strengths = strengths;
        this.suggestions = suggestions;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public int getAtsScore() {
        return atsScore;
    }


    public void setAtsScore(int atsScore) {
        this.atsScore = atsScore;
    }


    public String getStrengths() {
        return strengths;
    }


    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }


    public String getSuggestions() {
        return suggestions;
    }


    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }
}