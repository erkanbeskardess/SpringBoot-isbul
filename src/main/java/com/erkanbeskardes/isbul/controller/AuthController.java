package com.erkanbeskardes.isbul.controller;

import com.erkanbeskardes.isbul.business.dto.AuthRequestDto;
import com.erkanbeskardes.isbul.business.dto.AuthResponseDto;
import com.erkanbeskardes.isbul.business.dto.UsersDto;
import com.erkanbeskardes.isbul.repository.IUsersRepository;
import com.erkanbeskardes.isbul.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsersService usersService;
    private final IUsersRepository userRepository;


    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody UsersDto request) {
        return usersService.register(request);
    }

    @PostMapping(value = "/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        return usersService.login(request);

    }

}
