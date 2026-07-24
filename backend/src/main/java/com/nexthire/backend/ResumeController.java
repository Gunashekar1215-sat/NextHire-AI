package com.nexthire.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexthire.backend.model.Resume;
import com.nexthire.backend.service.ResumeService;

@RestController
@RequestMapping("/api")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/resume/test")
    public Resume testResume() {

        Resume resume = new Resume();
        resume.setName("Guna");
        resume.setSkills("Java, Spring Boot, MySQL");
        resume.setScore(85);

        return resumeService.saveResume(resume);
    }

    @PostMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Welcome to NextHire AI, " + name;
    }

    @GetMapping("/resume/analyze")
    public Map<String, Object> analyzeResume() {

        Map<String, Object> response = new HashMap<>();

        response.put("resumeScore", 75);
        response.put("strength", "Good Java Skills");

        response.put("suggestions", List.of(
                "Add more projects",
                "Add certifications",
                "Improve LinkedIn profile",
                "Add internship experience"
        ));

        return response;
    }

    @GetMapping("/resume/score")
    public Resume scoreResume(
            @RequestParam String name,
            @RequestParam String skills) {

        int score = skills.length() * 5;

        return new Resume(name, skills, score);
    }

    @PostMapping("/resume/save")
    public Resume saveResume(@RequestBody Resume resume) {

        return resumeService.saveResume(resume);
    }

    @GetMapping("/resume/ats-score")
    public Map<String, Object> getAtsScore(
            @RequestParam String skills) {

        Map<String, Object> response = new HashMap<>();

        int atsScore = skills.length() * 3;

        String status;

        if (atsScore >= 80) {
            status = "Excellent";
        } else if (atsScore >= 60) {
            status = "Good";
        } else {
            status = "Needs Improvement";
        }

        response.put("atsScore", atsScore);
        response.put("status", status);

        return response;
    }

    @GetMapping("/resumes")
    public List<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }
}