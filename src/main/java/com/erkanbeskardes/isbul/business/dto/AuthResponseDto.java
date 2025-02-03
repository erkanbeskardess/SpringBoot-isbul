package com.erkanbeskardes.isbul.business.dto;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.erkanbeskardes.isbul.business.entity.UsersEntity;
import lombok.Getter;

@Getter
public class AuthResponseDto {


    @JsonProperty("token")
    private String token;

    @JsonProperty("user")
    private UsersEntity user;



    public AuthResponseDto(String token, UsersEntity user) {
        this.token = token;
        this.user = user;
    }
    public AuthResponseDto(String token) {
        this.token = token;
    }

}
