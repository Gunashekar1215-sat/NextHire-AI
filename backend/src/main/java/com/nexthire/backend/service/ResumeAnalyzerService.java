package com.nexthire.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ResumeAnalyzerService {


    public Map<String, Object> analyze(String resumeText) {

        Map<String, Object> result = new HashMap<>();

        int score = 0;


        String text = resumeText.toLowerCase();


        // Skills checking
        if (text.contains("java")) {
            score += 10;
        }

        if (text.contains("python")) {
            score += 10;
        }

        if (text.contains("javascript")) {
            score += 10;
        }


        // Projects checking
        if (text.contains("project")) {
            score += 20;
        }


        // Education checking
        if (text.contains("education")) {
            score += 10;
        }


        // Certification checking
        if (text.contains("certification")) {
            score += 10;
        }


        // Soft skills
        if (text.contains("team")) {
            score += 10;
        }


        // Experience/project description
        if (resumeText.length() > 500) {
            score += 20;
        }


        result.put("ATS Score", score);


        result.put(
                "Strengths",
                "Good technical skills and project exposure"
        );


        result.put(
                "Suggestions",
                "Add more backend skills, GitHub links and certifications"
        );


        return result;
    }
}