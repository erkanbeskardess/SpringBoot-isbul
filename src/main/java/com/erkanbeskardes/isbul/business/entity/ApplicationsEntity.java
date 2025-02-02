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
    @JoinColumn(name = "job_postings_id")
    private JobPostingsEntity jobPosting;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    private String randomCode;

    @Enumerated(EnumType.STRING)
    private ApplicationStatusType applicationStatusType;

    @OneToMany(mappedBy = "fileName", cascade = CascadeType.ALL)
    private List<CvDocumentsEntity> cvDocuments;

    @PrePersist
    public void prePersist() {
        if (this.applicationStatusType == null) {
            this.applicationStatusType = ApplicationStatusType.PENDING;
        }
        if (this.systemCreatedBy == null) {
            this.systemCreatedBy = "system";
        }

    }

}
