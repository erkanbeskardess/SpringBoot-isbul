package com.erkanbeskardes.isbul.repository;

import com.erkanbeskardes.isbul.business.entity.JobPostingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobPostingsRepository extends JpaRepository<JobPostingsEntity, Long> {
}
