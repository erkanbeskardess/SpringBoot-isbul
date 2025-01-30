package com.erkanbeskardes.isbul.business.entity;


import com.erkanbeskardes.isbul.business.enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class RolesEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Roles roleName;
}
