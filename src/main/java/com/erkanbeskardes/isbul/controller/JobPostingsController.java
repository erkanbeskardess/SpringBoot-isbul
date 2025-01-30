package com.erkanbeskardes.isbul.controller;

import com.erkanbeskardes.isbul.business.dto.JobPostingsDto;
import com.erkanbeskardes.isbul.service.JobPostingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-postings")
@RequiredArgsConstructor
public class JobPostingsController {

    private final JobPostingsService jobPostingsService;

    @PreAuthorize("hasRole('HR')")
    @PostMapping("/create-job")
    public String createJobPost(@RequestBody JobPostingsDto jobPostDto) {
        jobPostingsService.createJobPosting(jobPostDto);
        return "Job post created successfully!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostingsDto> getJobPostingById(@PathVariable Long id) {
        return ResponseEntity.ok(jobPostingsService.getJobPostingById(id));
    }

    @PreAuthorize("hasRole('HR')")
    @GetMapping("/applications")
    public List<JobPostingsDto> listApplications() {
        return jobPostingsService.getAllJobPostings();
    }

}
