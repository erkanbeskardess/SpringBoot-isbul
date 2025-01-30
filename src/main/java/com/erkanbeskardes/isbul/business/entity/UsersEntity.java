package com.erkanbeskardes.isbul.business.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity(name = "users")
@Table(name = "users")
public class UsersEntity extends BaseEntity{

    @Column(name = "email", unique=true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationsEntity> applications;

    @Column(name = "role_id")
    private int roleId;

}
