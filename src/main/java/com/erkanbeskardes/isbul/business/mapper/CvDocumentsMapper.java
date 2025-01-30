package com.erkanbeskardes.isbul.business.mapper;

import com.erkanbeskardes.isbul.business.dto.CvDocumentsDto;
import com.erkanbeskardes.isbul.business.entity.CvDocumentsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CvDocumentsMapper {
    CvDocumentsMapper INSTANCE = Mappers.getMapper(CvDocumentsMapper.class);

    CvDocumentsEntity toEntity(CvDocumentsDto dto);

    CvDocumentsDto toDto(CvDocumentsEntity entity);
}
