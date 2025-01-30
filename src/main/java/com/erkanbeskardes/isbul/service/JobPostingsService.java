package com.erkanbeskardes.isbul.service;

import com.erkanbeskardes.isbul.business.dto.JobPostingsDto;
import com.erkanbeskardes.isbul.business.entity.JobPostingsEntity;
import com.erkanbeskardes.isbul.business.mapper.JobPostingsMapper;
import com.erkanbeskardes.isbul.repository.IJobPostingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPostingsService {

    private final IJobPostingsRepository jobPostingsRepository;
    private final JobPostingsMapper jobPostingsMapper;

    public JobPostingsDto createJobPosting(JobPostingsDto dto) {
        JobPostingsEntity jobPosting = jobPostingsMapper.jobPostingsDtoToJobPostingsEntity(dto);
        jobPosting = jobPostingsRepository.save(jobPosting);
        return jobPostingsMapper.jobPostingsEntityToJobPostingsDto(jobPosting);
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
