package com.erkanbeskardes.isbul.service;

import com.erkanbeskardes.isbul.business.dto.JobPostingsDto;
import com.erkanbeskardes.isbul.business.entity.JobPostingsEntity;
import com.erkanbeskardes.isbul.business.mapper.JobPostingsMapper;
import com.erkanbeskardes.isbul.repository.IJobPostingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPostingsService {

    private final IJobPostingsRepository jobPostingsRepository;
    private final JobPostingsMapper jobPostingsMapper;

    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public JobPostingsDto createJobPosting(JobPostingsDto dto) {
        JobPostingsEntity jobPosting = jobPostingsMapper.jobPostingsDtoToJobPostingsEntity(dto);
        jobPosting.setSystemCreatedBy(getCurrentUsername());
        jobPosting = jobPostingsRepository.save(jobPosting);
        return jobPostingsMapper.jobPostingsEntityToJobPostingsDto(jobPosting);
    }
    public JobPostingsDto updateJobPosting(JobPostingsDto dto,Long id) {
        // Mevcut iş ilanını veritabanından bul
        JobPostingsEntity existingJobPosting = jobPostingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Posting not found with id: " + id));

        // Güncellenecek alanları set et
        existingJobPosting.setTitle(dto.getTitle());
        existingJobPosting.setDescription(dto.getDescription());
        existingJobPosting.setLocation(dto.getLocation());
        existingJobPosting.setCompany(dto.getCompany());
        existingJobPosting.setStartDate(dto.getStartDate());
        existingJobPosting.setEndDate(dto.getEndDate());

        // Güncellenen kişi bilgisini ekleyelim
        existingJobPosting.setSystemCreatedBy(getCurrentUsername());

        // Güncellenmiş ilanı kaydet
        existingJobPosting = jobPostingsRepository.save(existingJobPosting);

        // DTO olarak geri döndür
        return jobPostingsMapper.jobPostingsEntityToJobPostingsDto(existingJobPosting);
    }

    public void deleteJobPosting(Long id) {
        jobPostingsRepository.deleteById(id);
    }


    public JobPostingsDto getJobPostingById(Long id) {
        JobPostingsEntity jobPosting = jobPostingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Posting not found"));
        return jobPostingsMapper.jobPostingsEntityToJobPostingsDto(jobPosting);
    }

    public List<JobPostingsDto> getAllJobPostings() {
        return jobPostingsRepository.findAll()
                .stream()
                .map(jobPostingsMapper::jobPostingsEntityToJobPostingsDto)
                .collect(Collectors.toList());
    }
}
