package com.erkanbeskardes.isbul.controller;

import com.erkanbeskardes.isbul.business.dto.AuthRequestDto;
import com.erkanbeskardes.isbul.business.dto.AuthResponseDto;
import com.erkanbeskardes.isbul.business.dto.UsersDto;
import com.erkanbeskardes.isbul.business.entity.UsersEntity;
import com.erkanbeskardes.isbul.repository.IUsersRepository;
import com.erkanbeskardes.isbul.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtAuthenticationFilter jwtService;

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody UsersDto request) {
        // Kullanıcı nesnesini oluştur
        UsersEntity user = UsersEntity.builder()
                .firstName(request.getFirstName())  // Kullanıcının kullanıcı adı
                .password(passwordEncoder.encode(request.getPassword()))  // Şifreyi encode et
                .role(request.getRole())  // Kullanıcı rolü
                .build();

        // Kullanıcıyı veritabanına kaydet
        userRepository.save(user);

        // JWT Token oluştur
        String token = jwtService.generateToken(user);

        // Token'ı döndür
        return new AuthResponseDto(token);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        // Kullanıcı kimlik doğrulaması
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Kullanıcıyı veritabanından bul
        UsersEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // JWT Token oluştur
        String token = jwtService.generateToken(user);

        // Token'ı döndür
        return new AuthResponseDto(token);
    }

}
