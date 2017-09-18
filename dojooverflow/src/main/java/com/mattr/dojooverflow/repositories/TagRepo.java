package com.mattr.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mattr.dojooverflow.models.Tag;

public interface TagRepo extends CrudRepository<Tag,Long> {

}
