package com.erkanbeskardes.isbul.service;

import com.erkanbeskardes.isbul.business.dto.CvDocumentsDto;
import com.erkanbeskardes.isbul.business.entity.CvDocumentsEntity;
import com.erkanbeskardes.isbul.business.mapper.CvDocumentsMapper;
import com.erkanbeskardes.isbul.repository.ICvDocumentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CvDocumentsService {

    private final ICvDocumentsRepository cvDocumentRepository;
    private final CvDocumentsMapper cvDocumentMapper;

    public CvDocumentsDto uploadCv(CvDocumentsDto dto) {
        CvDocumentsEntity cvDocument = cvDocumentMapper.toEntity(dto);
        cvDocument = cvDocumentRepository.save(cvDocument);
        return cvDocumentMapper.toDto(cvDocument);
    }

    public CvDocumentsDto getCvById(Long id) {
        CvDocumentsEntity cvDocument = cvDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CV Document not found"));
        return cvDocumentMapper.toDto(cvDocument);
    }

    public List<CvDocumentsDto> getAllCvs() {
        return cvDocumentRepository.findAll()
                .stream()
                .map(cvDocumentMapper::toDto)
                .collect(Collectors.toList());
    }
}
