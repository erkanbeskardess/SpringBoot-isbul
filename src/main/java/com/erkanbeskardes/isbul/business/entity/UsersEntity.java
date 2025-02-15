package com.erkanbeskardes.isbul.business.entity;

import com.erkanbeskardes.isbul.business.enums.Roles;
import jakarta.persistence.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity(name = "users")
@Table(name = "users")
public class UsersEntity extends BaseEntity implements UserDetails {

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

    @Column(name = "applications_id")
    private List<Long> applicationIds;

    @Column(name = "role")
    private Roles role;

    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = Roles.APPLICANT;  // Varsayılan değeri belirtiyoruz
        }
        if (this.systemCreatedBy == null) {
            this.systemCreatedBy = "system";
        }

    }

    //UserDetail...
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }
    @Override
    public String getPassword() {
        return "";
    }


    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
