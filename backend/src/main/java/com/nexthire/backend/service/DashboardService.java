package com.nexthire.backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nexthire.backend.repository.ResumeRepository;
import com.nexthire.backend.repository.UserRepository;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;

    public DashboardService(UserRepository userRepository,
                            ResumeRepository resumeRepository) {
        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
    }

    public Map<String, Object> getDashboardData() {

        Map<String, Object> response = new HashMap<>();

        response.put("totalUsers", userRepository.count());
        response.put("totalResumes", resumeRepository.count());
        response.put("averageAtsScore", 85);
        response.put("status", "System Running");

        return response;
    }
}