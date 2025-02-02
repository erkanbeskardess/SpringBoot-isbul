package com.erkanbeskardes.isbul.business.mapper;

import com.erkanbeskardes.isbul.business.dto.JobPostingsDto;
import com.erkanbeskardes.isbul.business.entity.JobPostingsEntity;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobPostingsMapper {

    public JobPostingsDto jobPostingsEntityToJobPostingsDto(JobPostingsEntity jobPostingsEntity) {
        JobPostingsDto jobPostingsDto = new JobPostingsDto();

        //base dto
        jobPostingsDto.setId(jobPostingsEntity.getId());
        jobPostingsDto.setSystemCreatedDate(jobPostingsEntity.getSystemCreatedDate());
        //jobPostingsDto.setSystemCreatedBy(jobPostingsEntity.getSystemCreatedBy());


        //Dto
        jobPostingsDto.setDescription(jobPostingsEntity.getDescription());
        jobPostingsDto.setTitle(jobPostingsEntity.getTitle());
        jobPostingsDto.setStartDate(jobPostingsEntity.getStartDate());
        jobPostingsDto.setCompany(jobPostingsEntity.getCompany());
        jobPostingsDto.setLocation(jobPostingsEntity.getLocation());
        jobPostingsDto.setEndDate(jobPostingsEntity.getEndDate());


        return jobPostingsDto;
    }

    public JobPostingsEntity jobPostingsDtoToJobPostingsEntity(JobPostingsDto jobPostingsDto) {
        JobPostingsEntity jobPostingsEntity = new JobPostingsEntity();

        //Base
        jobPostingsEntity.setId(jobPostingsDto.getId());
        jobPostingsEntity.setSystemCreatedDate(jobPostingsDto.getSystemCreatedDate());
        //jobPostingsEntity.setSystemCreatedBy(jobPostingsDto.getSystemCreatedBy());

        //Entity
        jobPostingsEntity.setDescription(jobPostingsDto.getDescription());
        jobPostingsEntity.setTitle(jobPostingsDto.getTitle());
        jobPostingsEntity.setCompany(jobPostingsDto.getCompany());
        jobPostingsEntity.setLocation(jobPostingsDto.getLocation());
        jobPostingsEntity.setStartDate(jobPostingsDto.getStartDate());
        jobPostingsEntity.setEndDate(jobPostingsDto.getEndDate());

        return jobPostingsEntity;
    }
}
