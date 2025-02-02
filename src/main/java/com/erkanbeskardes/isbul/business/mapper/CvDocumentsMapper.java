package com.erkanbeskardes.isbul.business.mapper;

import com.erkanbeskardes.isbul.business.dto.CvDocumentsDto;
import com.erkanbeskardes.isbul.business.entity.CvDocumentsEntity;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CvDocumentsMapper {

    public CvDocumentsEntity cvToEntity(CvDocumentsDto cvDto){
        CvDocumentsEntity cvEntity = new CvDocumentsEntity();

        cvEntity.setId(cvDto.getId());
        cvEntity.setFileName(cvDto.getFileName());
        cvEntity.setFileUrl(cvDto.getFileUrl());
        cvEntity.setFileType(cvDto.getFileType());
        cvEntity.setSystemCreatedDate(cvDto.getSystemCreatedDate());
        cvEntity.setSystemCreatedBy("systemCreatedBy");



        return cvEntity;



    }

    public CvDocumentsDto cvToDto(CvDocumentsEntity cvEntity){
        CvDocumentsDto cvDto = new CvDocumentsDto();

        cvDto.setId(cvEntity.getId());
        cvDto.setFileName(cvEntity.getFileName());
        cvDto.setFileUrl(cvEntity.getFileUrl());
        cvDto.setFileType(cvEntity.getFileType());
        cvDto.setSystemCreatedDate(cvEntity.getSystemCreatedDate());
        cvDto.setSystemCreatedBy("systemCreatedBy");

        return cvDto;



    }
}
