package com.erkanbeskardes.isbul.repository;

import com.erkanbeskardes.isbul.business.dto.ApplicationsDto;
import com.erkanbeskardes.isbul.business.entity.ApplicationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IApplicationsRepository  extends JpaRepository<ApplicationsEntity,Long> {

    ApplicationsDto findByRandomCode(String randomCode);

}
