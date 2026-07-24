package com.nexthire.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexthire.backend.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}