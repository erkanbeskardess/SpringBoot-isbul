package com.erkanbeskardes.isbul.business.dto;

import com.erkanbeskardes.isbul.business.entity.JobPostingsEntity;
import com.erkanbeskardes.isbul.business.enums.ApplicationStatusType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationsDto extends BaseDto {
    private JobPostingsEntity jobPosting;
    private ApplicationStatusType applicationStatusType;
    private String randomCode;
}












