package com.erkanbeskardes.isbul.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CvDocumentsDto  extends BaseDto{
    private String fileName;
    private String fileType;
    private String fileUrl;
}

