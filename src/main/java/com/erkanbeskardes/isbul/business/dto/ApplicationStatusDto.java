package com.erkanbeskardes.isbul.business.dto;
import com.erkanbeskardes.isbul.business.enums.ApplicationStatusType;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStatusDto {

    private Long id;
    private ApplicationStatusType statusName;
}
