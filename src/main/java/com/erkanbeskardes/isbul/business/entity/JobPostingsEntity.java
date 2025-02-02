package com.erkanbeskardes.isbul.business.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name = "publishedDate")
    private LocalDate publishedDate;

    @Column(name = "company")
    private String company;

    @Column(name = "location")
    private String location;
}