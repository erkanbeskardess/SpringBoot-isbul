package com.erkanbeskardes.isbul.service;

import com.erkanbeskardes.isbul.business.dto.ApplicationsDto;
import com.erkanbeskardes.isbul.business.entity.ApplicationsEntity;
import com.erkanbeskardes.isbul.business.enums.ApplicationStatusType;
import com.erkanbeskardes.isbul.business.mapper.ApplicationsMapper;
import com.erkanbeskardes.isbul.repository.IApplicationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationsService {

    private final IApplicationsRepository applicationRepository;
    private final ApplicationsMapper applicationMapper;




    public String createApplication(ApplicationsDto dto) {

        ApplicationsEntity application = applicationMapper.applicationsDtoToApplicationEntity(dto);
        application.setSystemCreatedBy(UUID.randomUUID().toString());
        SecureRandom secureRandom = new SecureRandom();
        Integer code = 100000 + secureRandom.nextInt(900000);
        application.setRandomCode(String.valueOf(code));
         applicationRepository.save(application);

                return String.valueOf(code);
    }

    public ApplicationsDto getApplicationById(Long id) {
        ApplicationsEntity application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        return applicationMapper.applicationsEntityToApplicationDto(application);
    }

    public List<ApplicationsDto> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(applicationMapper::applicationsEntityToApplicationDto)
                .collect(Collectors.toList());
    }
    public ResponseEntity<ApplicationsDto> getApplicationByCode(String randomCode) {
        ApplicationsDto dto = applicationMapper.applicationsEntityToApplicationDto(applicationRepository.findByRandomCode(randomCode));
            return ResponseEntity.ok().body(dto);

    }


    public void apply(ApplicationsDto applicationDto) {
        ApplicationsEntity application = new ApplicationsEntity();
        application.setJobPosting(applicationDto.getJobPosting());

        application.setRandomCode(UUID.randomUUID().toString()); // Rastgele kod oluştur
        application.setApplicationStatusType(ApplicationStatusType.PENDING); // İlk başvuru durumu "PENDING"

        applicationRepository.save(application);
    }
}
