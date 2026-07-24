package com.nexthire.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexthire.backend.model.ResumeAnalysis;

public interface ResumeAnalysisRepository 
        extends JpaRepository<ResumeAnalysis, Long> {

}