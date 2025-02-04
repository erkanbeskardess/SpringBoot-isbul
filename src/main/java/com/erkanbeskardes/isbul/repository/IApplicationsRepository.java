package com.erkanbeskardes.isbul.repository;

import com.erkanbeskardes.isbul.business.entity.ApplicationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IApplicationsRepository extends JpaRepository<ApplicationsEntity, Long> {

    ApplicationsEntity findByRandomCode(String randomCode);

    ApplicationsEntity findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT random_code FROM applications WHERE job_postings_id = :JobPostingId AND user_id = :userId", nativeQuery = true)
    String getRandomCodeByJobPostingIdAndUserId(@Param("JobPostingId") Long JobPostingId, @Param("userId") Long userId);


    Optional<ApplicationsEntity> findByJobPostingIdAndUserId(Long jobPostingId, Long userId);

}
