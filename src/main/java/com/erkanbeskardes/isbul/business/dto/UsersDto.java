package com.erkanbeskardes.isbul.business.dto;

import com.erkanbeskardes.isbul.business.entity.ApplicationsEntity;
import com.erkanbeskardes.isbul.business.enums.Roles;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsersDto extends BaseDto {


    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Roles role;
    private List<ApplicationsEntity> applications;
}
