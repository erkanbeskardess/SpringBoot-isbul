package com.erkanbeskardes.isbul.repository;

import com.erkanbeskardes.isbul.business.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUsersRepository extends JpaRepository<UsersEntity, Long> {
}
