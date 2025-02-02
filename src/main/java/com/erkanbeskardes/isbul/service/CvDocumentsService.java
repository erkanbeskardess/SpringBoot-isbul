package com.erkanbeskardes.isbul.service;

import com.erkanbeskardes.isbul.business.dto.CvDocumentsDto;
import com.erkanbeskardes.isbul.business.entity.CvDocumentsEntity;
import com.erkanbeskardes.isbul.business.mapper.CvDocumentsMapper;
import com.erkanbeskardes.isbul.repository.ICvDocumentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CvDocumentsService {

    private final ICvDocumentsRepository cvDocumentRepository;
    private final CvDocumentsMapper cvDocumentMapper;

    private static final String UPLOAD_DIR = "uploads/"; // Kaydedilecek klasör


        public ResponseEntity<String> uploadCv(CvDocumentsDto dto,MultipartFile file) {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Dosya yüklenemedi!");
            }
            CvDocumentsEntity cvDocument = cvDocumentMapper.cvToEntity(dto);
            cvDocumentRepository.save(cvDocument);
            try {
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                Path filePath = Path.of(UPLOAD_DIR + file.getOriginalFilename());

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                return ResponseEntity.ok("Dosya başarıyla kaydedildi: " + filePath.toString());

            } catch (IOException e) {
                return ResponseEntity.internalServerError().body("Dosya kaydedilirken hata oluştu!");
            }
        }


    public CvDocumentsDto getCvById(Long id) {
        CvDocumentsEntity cvDocument = cvDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CV Document not found"));
        return cvDocumentMapper.cvToDto(cvDocument);
    }

    public List<CvDocumentsDto> getAllCvs() {
        return cvDocumentRepository.findAll()
                .stream()
                .map(cvDocumentMapper::cvToDto)
                .collect(Collectors.toList());
    }

    public void deleteCv(Long id) {
          cvDocumentRepository.deleteById(id);
    }
}
