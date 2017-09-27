package com.mattr.pollLitHub.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.pollLitHub.models.Title;

@Repository
public interface TitleRepo extends CrudRepository<Title,Long>{

}
