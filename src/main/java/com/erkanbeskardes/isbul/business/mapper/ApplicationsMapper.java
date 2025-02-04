package com.erkanbeskardes.isbul.business.mapper;

import com.erkanbeskardes.isbul.business.dto.ApplicationsDto;
import com.erkanbeskardes.isbul.business.entity.ApplicationsEntity;
import com.erkanbeskardes.isbul.business.entity.UsersEntity;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ApplicationsMapper {
    public ApplicationsDto applicationsEntityToApplicationDto(ApplicationsEntity applicationsEntity) {
        ApplicationsDto applicationsDto = new ApplicationsDto();

        //base dto
        applicationsDto.setId(applicationsEntity.getId());
        applicationsDto.setSystemCreatedDate(applicationsEntity.getSystemCreatedDate());
        applicationsDto.setUserId(applicationsEntity.getUser().getId());
        //applicationsDto.setSystemCreatedBy(applicationsEntity.getSystemCreatedBy());

        //TodoDto
        applicationsDto.setRandomCode(applicationsEntity.getRandomCode());
        applicationsDto.setApplicationStatusType(applicationsEntity.getApplicationStatusType());
        applicationsDto.setJobPosting(applicationsEntity.getJobPosting());


        return applicationsDto;
    }


    public ApplicationsEntity applicationsDtoToApplicationEntity(ApplicationsDto applicationsDto, UsersEntity usersEntity, Long cvId) {
        ApplicationsEntity applicationsEntity = new ApplicationsEntity();

        //applicationsDto
        applicationsEntity.setId(applicationsDto.getId());
        applicationsEntity.setSystemCreatedDate(applicationsDto.getSystemCreatedDate());
        applicationsEntity.setUser(usersEntity);
        if (applicationsEntity.getCvDocumentIds() == null) {
            applicationsEntity.setCvDocumentIds(new ArrayList<>());
        }
        applicationsEntity.getCvDocumentIds().add(cvId);

        //applicationsEntity.setSystemCreatedBy(applicationsDto.getSystemCreatedBy());


        //applicationsEntity
        applicationsEntity.setRandomCode(applicationsDto.getRandomCode());
        applicationsEntity.setJobPosting(applicationsDto.getJobPosting());
        applicationsEntity.setApplicationStatusType(applicationsDto.getApplicationStatusType());


        return applicationsEntity;
    }
}
