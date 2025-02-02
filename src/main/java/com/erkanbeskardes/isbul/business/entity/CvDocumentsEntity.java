package com.erkanbeskardes.isbul.business.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class CvDocumentsEntity extends BaseEntity{
    private String fileName;
    private String fileType;
    private String fileUrl;      // todo Dosyanın saklandığı URL (AWS S3)

}
