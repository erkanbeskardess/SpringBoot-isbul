package com.erkanbeskardes.isbul.business.mapper;

import com.erkanbeskardes.isbul.business.dto.UsersDto;
import com.erkanbeskardes.isbul.business.entity.UsersEntity;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersMapper {

    public UsersDto usersEntityToUsersDto(UsersEntity usersEntity){
        UsersDto usersDto = new UsersDto();

        //base dto
        usersDto.setId(usersEntity.getId());
        usersDto.setSystemCreatedDate(usersEntity.getSystemCreatedDate());
       // usersDto.setSystemCreatedBy(String.valueOf(usersEntity.getSystemCreatedBy()));


        //TodoDto
        usersDto.setEmail(usersEntity.getEmail());
        usersDto.setApplications(usersEntity.getApplications());
        usersDto.setFirstName(usersEntity.getFirstName());
        usersDto.setLastName(usersEntity.getLastName());
        usersDto.setPassword(usersEntity.getPassword());
        usersDto.setPhone(usersEntity.getPhone());
        usersDto.setRole(usersEntity.getRole());



        return usersDto;
    }

    public UsersEntity usersDtoToUsersEntity(UsersDto usersDto){
        UsersEntity usersEntity = new UsersEntity();

        //UsersDto
        usersEntity.setId(usersDto.getId());
        usersEntity.setSystemCreatedDate(usersDto.getSystemCreatedDate());
        //usersEntity.setSystemCreatedBy(String.valueOf(usersDto.getSystemCreatedBy()));


        //UsersEntity
        usersEntity.setEmail(usersDto.getEmail());
        usersEntity.setApplications(usersDto.getApplications());
        usersEntity.setFirstName(usersDto.getFirstName());
        usersEntity.setLastName(usersDto.getLastName());
        usersEntity.setPassword(usersDto.getPassword());
        usersEntity.setPhone(usersDto.getPhone());
        usersEntity.setRole(usersDto.getRole());


        return usersEntity;
    }
}

