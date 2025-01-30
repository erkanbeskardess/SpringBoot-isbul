package com.erkanbeskardes.isbul.controller;

import com.erkanbeskardes.isbul.business.dto.UsersDto;
import com.erkanbeskardes.isbul.business.entity.UsersEntity;
import com.erkanbeskardes.isbul.repository.IUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IUsersRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-role/{userId}")
    public String updateUserRole(@PathVariable Long userId, @RequestBody UsersDto request) {
        UsersEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(request.getRole());
        userRepository.save(user);

        return "User role updated successfully!";
    }
}
