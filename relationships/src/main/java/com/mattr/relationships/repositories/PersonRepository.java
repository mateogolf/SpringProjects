package com.mattr.relationships.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.mattr.relationships.models.Person;

public interface PersonRepository extends CrudRepository<Person,Long> {
	ArrayList<Person> findByLicenseIsNull();
}
