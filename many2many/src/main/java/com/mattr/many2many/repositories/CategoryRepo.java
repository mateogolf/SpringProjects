package com.mattr.many2many.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.many2many.models.Category;
@Repository
public interface CategoryRepo extends CrudRepository<Category,Long>{

}
