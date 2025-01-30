package com.erkanbeskardes.isbul.repository;

import com.erkanbeskardes.isbul.business.entity.CvDocumentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICvDocumentsRepository extends JpaRepository<CvDocumentsEntity, Long> {
}
