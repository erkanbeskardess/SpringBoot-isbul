package com.erkanbeskardes.isbul.business.dto;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.erkanbeskardes.isbul.business.enums.Roles;


public class AuthResponseDto {

    @JsonProperty("token")
    private String token;

    @JsonProperty("role")
    private Roles role;

    public AuthResponseDto(String token, Roles role) {
        this.token = token;
        this.role = role;
    }
    public AuthResponseDto(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
    public Roles getRole() { return role; }

}
