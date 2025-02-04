package com.erkanbeskardes.isbul.service;

import com.erkanbeskardes.isbul.business.dto.ApplicationsDto;
import com.erkanbeskardes.isbul.business.entity.ApplicationsEntity;
import com.erkanbeskardes.isbul.business.entity.UsersEntity;
import com.erkanbeskardes.isbul.business.enums.ApplicationStatusType;
import com.erkanbeskardes.isbul.business.mapper.ApplicationsMapper;
import com.erkanbeskardes.isbul.business.mapper.UsersMapper;
import com.erkanbeskardes.isbul.repository.IApplicationsRepository;
import com.erkanbeskardes.isbul.repository.IUsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationsService {

    private final IApplicationsRepository applicationRepository;
    private final IUsersRepository usersRepository;
    private final ApplicationsMapper applicationMapper;
    private final UsersService usersService;
    private final UsersMapper usersMapper;

    @Transactional
    public String createApplication(ApplicationsDto dto) {
        UsersEntity user = usersMapper.usersDtoToUsersEntity(usersService.getUserById(dto.getUserId()));
        ApplicationsEntity application = applicationMapper.applicationsDtoToApplicationEntity(dto, user, dto.getCvId());

        if (application.getCvDocumentIds() == null) {
            application.setCvDocumentIds(new ArrayList<>());
        }
        applicationRepository.addCvIdToApplication(application.getJobPosting().getId(), application.getUser().getId(), dto.getCvId());


        if (checkApplication(dto, user.getId())) {
            return applicationRepository.getRandomCodeByJobPostingIdAndUserId(application.getJobPosting().getId(), user.getId()) + "1";
        }

        application.setSystemCreatedBy(UUID.randomUUID().toString());
        SecureRandom secureRandom = new SecureRandom();
        Integer code = 100000 + secureRandom.nextInt(900000);
        application.setRandomCode(String.valueOf(code));


        applicationRepository.save(application);

        if (user.getApplicationIds() == null) {
            user.setApplicationIds(new ArrayList<>());
        }

        user.getApplicationIds().add(application.getId());
        usersRepository.save(user);
        return String.valueOf(code);
    }

    @Transactional
    public ResponseEntity<String> changeRoleStatus(Long applicationId, String newRoleStatus) {
        String output = newRoleStatus.replace("\"", "");

        Integer result = applicationRepository.updateRoleStatus(applicationId, output);

        if (result == 1) {
            return ResponseEntity.ok().body("Başvuru Durum Güncellemesi Başarılı");
        } else {
            return ResponseEntity.badRequest().build();
        }


    }

    public ApplicationsDto getApplicationById(Long id) {
        ApplicationsEntity application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        return applicationMapper.applicationsEntityToApplicationDto(application);
    }

    public boolean checkApplication(ApplicationsDto dto, Long userId) {

        Optional<ApplicationsEntity> existingApplication = applicationRepository.findByJobPostingIdAndUserId(dto.getJobPosting().getId(), userId);
        return existingApplication.isPresent();


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
