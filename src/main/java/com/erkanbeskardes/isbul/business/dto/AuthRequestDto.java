package com.erkanbeskardes.isbul.business.dto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private long userId;
    private String username;
    private String password;
}
