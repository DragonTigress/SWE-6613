package com.registration.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registration.app.entity.Attribute;

@Repository
public interface PlayerAttributesRepository extends CrudRepository<Attribute, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM ATTRIBUTE WHERE EMAIL_ADDRESS = :emailAddress")
	Attribute readPlayerAttributeRecordsByEmailAddress(@Param("emailAddress") String emailAddress);

}
