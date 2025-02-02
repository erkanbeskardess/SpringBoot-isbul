package com.erkanbeskardes.isbul.controller;

import com.erkanbeskardes.isbul.business.dto.ApplicationsDto;
import com.erkanbeskardes.isbul.service.ApplicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hr/applications")
@RequiredArgsConstructor
public class ApplicationsController {

    private final ApplicationsService applicationService;

    @PostMapping
    public ResponseEntity<String> createApplication(@RequestBody ApplicationsDto dto) {
       String code = applicationService.createApplication(dto);
        return ResponseEntity.ok().body(code);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationsDto> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationsDto>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    //@PreAuthorize("hasRole('APPLICANT')")
    @PostMapping("/apply")
    public String applyForJob(@RequestBody ApplicationsDto applicationDto) {
        applicationService.apply(applicationDto);
        return "Application submitted successfully!";
    }

    //@PreAuthorize("hasRole('APPLICANT')")
    @GetMapping("/status/{randomCode}")
    public ResponseEntity<ApplicationsDto> checkApplicationStatus(@PathVariable String randomCode) {
        return applicationService.getApplicationByCode(randomCode);
    }
}

