package com.nexthire.backend;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(
            @RequestParam("file") MultipartFile file) {

        try {

            // Project root/uploads folder
            String uploadDir = System.getProperty("user.dir")
                    + File.separator
                    + "uploads"
                    + File.separator;

            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            System.out.println("Upload Directory: " + dir.getAbsolutePath());

            File destination = new File(dir, file.getOriginalFilename());

            file.transferTo(destination);

            return ResponseEntity.ok(
                    "Resume uploaded successfully!\n"
                            + "Saved At: "
                            + destination.getAbsolutePath());

        } catch (IOException e) {

            e.printStackTrace();

            return ResponseEntity.badRequest()
                    .body("Upload failed! " + e.getMessage());
        }
    }
}