package com.registration.app.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.registration.app.entity.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM LOGIN WHERE EMAIL_ADDRESS = :emailAddress")
	Login checkStatusByEmailAddress(@Param("emailAddress") String emailAddress);
	
	@Query(nativeQuery = true, value = "UPDATE LOGIN SET STATUS = 'INACTIVE' WHERE EMAIL_ADDRESS = :emailAddress")
	@Modifying
	@Transactional
	int logoutUserByEmailAddress(@Param("emailAddress") String emailAddress);		

}
