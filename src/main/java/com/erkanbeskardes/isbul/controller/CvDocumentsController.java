package com.erkanbeskardes.isbul.controller;

import com.erkanbeskardes.isbul.business.dto.CvDocumentsDto;
import com.erkanbeskardes.isbul.service.CvDocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/applicant/cv-documents")
@RequiredArgsConstructor
public class CvDocumentsController {

    private final CvDocumentsService cvDocumentService;


    @PostMapping
    public ResponseEntity<String> uploadCv(@RequestPart("file") MultipartFile file,@RequestPart("data") CvDocumentsDto dto) {
        return cvDocumentService.uploadCv(dto,file);
    }

    @GetMapping("/{id}")
    public CvDocumentsDto getCvById(@PathVariable Long id) {
        return cvDocumentService.getCvById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteCv(@PathVariable Long id) {
        cvDocumentService.deleteCv(id);
    }

    @GetMapping
    public ResponseEntity<List<CvDocumentsDto>> getAllCvs() {
        return ResponseEntity.ok(cvDocumentService.getAllCvs());
    }
}
