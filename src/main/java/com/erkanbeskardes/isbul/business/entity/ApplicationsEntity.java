package com.erkanbeskardes.isbul.business.entity;

import com.erkanbeskardes.isbul.business.enums.ApplicationStatusType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity(name = "applications")
@Table(name = "applications")
public class ApplicationsEntity extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "job_posting_id")
    private JobPostingsEntity jobPosting;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    private String randomCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatusType applicationStatusType;

    @OneToMany(mappedBy = "applications", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CvDocumentsEntity> cvDocuments;

}
