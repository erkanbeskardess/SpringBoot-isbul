package com.erkanbeskardes.isbul.business.entity;

import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//(İK personelinin açtığı iş ilanları)
@Entity(name = "job_postings")
@Table(name = "job_postings")

public class JobPostingsEntity extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "company")
    private String company;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "jobPosting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationsEntity> applications;

}