package com.registration.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.registration.app.entity.Attribute;

@Repository
public interface PlayerAttributesRepository extends CrudRepository<Attribute, Integer> {

}
