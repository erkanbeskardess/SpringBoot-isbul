package com.erkanbeskardes.isbul.repository;

import com.erkanbeskardes.isbul.business.entity.ApplicationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(value = "UPDATE applications " +
            "SET cv_document_ids = array_append(cv_document_ids, :cvId) " +
            "WHERE job_postings_id = :jobPostingId " +
            "AND user_id = :userId " +
            "AND NOT (:cvId = ANY(cv_document_ids))" , nativeQuery = true)
    void addCvIdToApplication(@Param("jobPostingId") Long jobPostingId,
                              @Param("userId") Long userId,
                              @Param("cvId") Long cvId);



    Optional<ApplicationsEntity> findByJobPostingIdAndUserId(Long jobPostingId, Long userId);

}
