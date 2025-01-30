package com.erkanbeskardes.isbul.business.dto;

import com.erkanbeskardes.isbul.business.entity.JobPostingsEntity;
import com.erkanbeskardes.isbul.business.entity.UsersEntity;
import com.erkanbeskardes.isbul.business.enums.ApplicationStatusType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationsDto extends BaseDto {
    private Long id;
    private JobPostingsEntity jobPosting; // İlan
    private UsersEntity user; // Başvuran Kullanıcı
    private ApplicationStatusType applicationStatusType; // Enum
    private String randomCode;
}












