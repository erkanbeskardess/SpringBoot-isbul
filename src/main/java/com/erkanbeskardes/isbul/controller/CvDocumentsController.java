package com.erkanbeskardes.isbul.controller;

import com.erkanbeskardes.isbul.business.dto.CvDocumentsDto;
import com.erkanbeskardes.isbul.service.CvDocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cv-documents")
@RequiredArgsConstructor
public class CvDocumentsController {

    private final CvDocumentsService cvDocumentService;

    @PostMapping
    public ResponseEntity<CvDocumentsDto> uploadCv(@RequestBody CvDocumentsDto dto) {
        return ResponseEntity.ok(cvDocumentService.uploadCv(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CvDocumentsDto> getCvById(@PathVariable Long id) {
        return ResponseEntity.ok(cvDocumentService.getCvById(id));
    }

    @GetMapping
    public ResponseEntity<List<CvDocumentsDto>> getAllCvs() {
        return ResponseEntity.ok(cvDocumentService.getAllCvs());
    }
}
