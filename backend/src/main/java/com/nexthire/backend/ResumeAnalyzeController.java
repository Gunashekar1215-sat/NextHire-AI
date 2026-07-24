package com.nexthire.backend;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nexthire.backend.model.ResumeAnalysis;
import com.nexthire.backend.repository.ResumeAnalysisRepository;
import com.nexthire.backend.service.ResumeAnalyzerService;

@RestController
@RequestMapping("/api/resume")
public class ResumeAnalyzeController {


    private final ResumeAnalyzerService resumeAnalyzerService;

    private final ResumeAnalysisRepository resumeAnalysisRepository;


    public ResumeAnalyzeController(
            ResumeAnalyzerService resumeAnalyzerService,
            ResumeAnalysisRepository resumeAnalysisRepository) {

        this.resumeAnalyzerService = resumeAnalyzerService;
        this.resumeAnalysisRepository = resumeAnalysisRepository;
    }


    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeResume(
            @RequestParam("file") MultipartFile file) {

        try {

            // Create uploads folder
            Path uploadPath = Paths.get("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }


            // Save resume file
            Path filePath = uploadPath.resolve(file.getOriginalFilename());

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );


            File savedFile = filePath.toFile();


            // Extract text
            String resumeText =
                    ResumeTextExtractor.extractText(savedFile);


            // Analyze resume
            Map<String, Object> analysis =
                    resumeAnalyzerService.analyze(resumeText);



            // Save analysis into MySQL
            ResumeAnalysis resumeAnalysis =
                    new ResumeAnalysis(

                    file.getOriginalFilename(),

                    (int) analysis.get("ATS Score"),

                    analysis.get("Strengths").toString(),

                    analysis.get("Suggestions").toString()
            );


            resumeAnalysisRepository.save(resumeAnalysis);



            return ResponseEntity.ok(analysis);



        } catch (Exception e) {


            e.printStackTrace();


            return ResponseEntity
                    .badRequest()
                    .body(
                    "Error Type: "
                    + e.getClass().getName()
                    + "\nMessage: "
                    + e.getMessage()
                    );
        }
    }
}