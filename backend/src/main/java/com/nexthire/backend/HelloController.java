package com.nexthire.backend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "NextHire AI Backend Running Successfully!";
    }

    @GetMapping("/api/test")
    public String test() {
        return "NextHire AI API Working";
    }

    @GetMapping("/api/info")
    public Map<String, String> info() {

        Map<String, String> response = new HashMap<>();

        response.put("project", "NextHire AI");
        response.put("version", "1.0");
        response.put("status", "Running");

        return response;
    }
}