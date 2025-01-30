package com.erkanbeskardes.isbul.business.dto;


import com.erkanbeskardes.isbul.business.entity.ApplicationsEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingsDto extends BaseDto {

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String company;

    private String location;

    private List<ApplicationsEntity> applications;

    private String createdBy;
}
