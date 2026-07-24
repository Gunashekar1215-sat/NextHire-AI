package com.nexthire.backend;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexthire.backend.model.ResumeAnalysis;
import com.nexthire.backend.repository.ResumeAnalysisRepository;


@RestController
@RequestMapping("/api/resume")
public class ResumeHistoryController {


    private final ResumeAnalysisRepository resumeAnalysisRepository;


    public ResumeHistoryController(
            ResumeAnalysisRepository resumeAnalysisRepository) {

        this.resumeAnalysisRepository = resumeAnalysisRepository;
    }


    @GetMapping("/history")
    public ResponseEntity<List<ResumeAnalysis>> getResumeHistory() {


        List<ResumeAnalysis> history =
                resumeAnalysisRepository.findAll();


        return ResponseEntity.ok(history);
    }

}