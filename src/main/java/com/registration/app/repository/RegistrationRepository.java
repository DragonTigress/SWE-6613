package com.registration.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registration.app.entity.Registration;

@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM REGISTRATION WHERE EMAIL_ADDRESS = :emailAddress")
	Registration readRegistrationRecordsByEmailAddress(@Param("emailAddress") String emailAddress);
	
}
