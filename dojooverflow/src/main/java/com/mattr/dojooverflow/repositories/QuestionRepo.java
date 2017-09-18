package com.mattr.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mattr.dojooverflow.models.Question;

public interface QuestionRepo extends CrudRepository<Question,Long>{

}
