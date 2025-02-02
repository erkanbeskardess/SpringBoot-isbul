package com.erkanbeskardes.isbul.business.dto;


import lombok.*;

import java.time.LocalDate;

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

    private String createdBy;
}
