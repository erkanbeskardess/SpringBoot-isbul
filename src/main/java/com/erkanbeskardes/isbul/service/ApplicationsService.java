package com.erkanbeskardes.isbul.service;

import com.erkanbeskardes.isbul.business.dto.ApplicationsDto;
import com.erkanbeskardes.isbul.business.entity.ApplicationsEntity;
import com.erkanbeskardes.isbul.business.enums.ApplicationStatusType;
import com.erkanbeskardes.isbul.business.mapper.ApplicationsMapper;
import com.erkanbeskardes.isbul.repository.IApplicationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationsService {

    private final IApplicationsRepository applicationRepository;
    private final ApplicationsMapper applicationMapper;

    public ApplicationsDto createApplication(ApplicationsDto dto) {
        ApplicationsEntity application = applicationMapper.applicationsDtoToApplicationEntity(dto);
        application = applicationRepository.save(application);
        return applicationMapper.applicationsEntityToApplicationDto(application);
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
    public ApplicationsDto getApplicationByCode(String randomCode) {

            return applicationRepository.findByRandomCode(randomCode);

    }

    public void apply(ApplicationsDto applicationDto) {
        ApplicationsEntity application = new ApplicationsEntity();
        application.setUser(applicationDto.getUser());
        application.setJobPosting(applicationDto.getJobPosting());

        application.setRandomCode(UUID.randomUUID().toString()); // Rastgele kod oluştur
        application.setApplicationStatusType(ApplicationStatusType.PENDING); // İlk başvuru durumu "PENDING"

        applicationRepository.save(application);
    }
}
