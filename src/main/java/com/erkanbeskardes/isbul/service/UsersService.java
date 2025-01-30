package com.erkanbeskardes.isbul.service;

import com.erkanbeskardes.isbul.business.dto.UsersDto;
import com.erkanbeskardes.isbul.business.entity.UsersEntity;
import com.erkanbeskardes.isbul.business.mapper.UsersMapper;
import com.erkanbeskardes.isbul.repository.IUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final IUsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public  UsersDto createUser(UsersDto dto) {
        UsersEntity entityUser = usersMapper.usersDtoToUsersEntity(dto);
        entityUser = usersRepository.save(entityUser);
        return usersMapper.usersEntityToUsersDto(entityUser);
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
