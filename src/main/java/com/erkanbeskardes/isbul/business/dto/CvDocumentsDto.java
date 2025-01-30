package com.erkanbeskardes.isbul.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CvDocumentsDto {
    private Long applicationId;
    private String fileName;
    private String fileType;
    private String fileUrl;
}

