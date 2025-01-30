package com.erkanbeskardes.isbul.business.controller;

import com.erkanbeskardes.isbul.business.dto.ApplicationsDto;
import com.erkanbeskardes.isbul.service.ApplicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationsController {

    private final ApplicationsService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationsDto> createApplication(@RequestBody ApplicationsDto dto) {
        return ResponseEntity.ok(applicationService.createApplication(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationsDto> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationsDto>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }
    @PreAuthorize("hasRole('APPLICANT')")
    @PostMapping("/apply")
    public String applyForJob(@RequestBody ApplicationsDto applicationDto) {
        applicationService.apply(applicationDto);
        return "Application submitted successfully!";
    }

    @PreAuthorize("hasRole('APPLICANT')")
    @GetMapping("/status/{randomCode}")
    public ApplicationsDto checkApplicationStatus(@PathVariable String randomCode) {
        return applicationService.getApplicationByCode(randomCode);
    }
}

