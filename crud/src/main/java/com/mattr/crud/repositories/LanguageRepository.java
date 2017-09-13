package com.mattr.crud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.crud.models.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long> {

}
