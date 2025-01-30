package com.erkanbeskardes.isbul.business.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class CvDocumentsEntity extends BaseEntity{
    private String fileName;     // Dosya adı
    private String fileType;     // Dosya türü (PDF, DOCX vb.)
    private String fileUrl;      // Dosyanın saklandığı URL (AWS S3, Google Cloud vb.)

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationsEntity application;


}
