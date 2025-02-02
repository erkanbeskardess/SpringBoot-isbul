package com.erkanbeskardes.isbul.controller;

import com.erkanbeskardes.isbul.business.dto.JobPostingsDto;
import com.erkanbeskardes.isbul.service.JobPostingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hr/job-postings")
@RequiredArgsConstructor
public class JobPostingsController {

    private final JobPostingsService jobPostingsService;

    //@PreAuthorize("hasRole('HR')")
    @PostMapping("/create-job")
    public JobPostingsDto createJobPost(@RequestBody JobPostingsDto jobPostDto) {
        return jobPostingsService.createJobPosting(jobPostDto);
    }

    @PutMapping("/update-job/{id}")
    public JobPostingsDto updateJobPost(@RequestBody JobPostingsDto jobPostDto, @PathVariable Long id) {
        return jobPostingsService.updateJobPosting(jobPostDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        jobPostingsService.deleteJobPosting(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostingsDto> getJobPostingById(@PathVariable Long id) {
        return ResponseEntity.ok(jobPostingsService.getJobPostingById(id));
    }

    @GetMapping("/jobs")
    public List<JobPostingsDto> listApplications() {
        return jobPostingsService.getAllJobPostings();
    }

}
