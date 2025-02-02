package com.erkanbeskardes.isbul.service;

import com.erkanbeskardes.isbul.business.dto.AuthRequestDto;
import com.erkanbeskardes.isbul.business.dto.AuthResponseDto;
import com.erkanbeskardes.isbul.business.dto.UsersDto;
import com.erkanbeskardes.isbul.business.entity.UsersEntity;
import com.erkanbeskardes.isbul.business.mapper.UsersMapper;
import com.erkanbeskardes.isbul.repository.IUsersRepository;
import com.erkanbeskardes.isbul.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final IUsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationFilter jwtService;


    public  AuthResponseDto register(UsersDto dto) {

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        UsersEntity entityUser = usersMapper.usersDtoToUsersEntity(dto);
        entityUser.setSystemCreatedBy("system");
        String token = jwtService.generateToken(entityUser);

        usersRepository.save(entityUser);


        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
         /*
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );*/

        UsersEntity user = usersRepository.findAllByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // JWT Token
        String token = jwtService.generateToken(user);
        System.out.println("JWT Token: " + token);

        return new AuthResponseDto(token,user.getRole());

    }

        public UsersDto getUserById(Long id) {
        UsersEntity user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return usersMapper.usersEntityToUsersDto(user);
    }

    public List<UsersDto> getAllUsers() {
        return usersRepository.findAll()
                .stream()
                .map(usersMapper::usersEntityToUsersDto)
                .collect(Collectors.toList());
    }
}
