package com.mattr.many2many.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.many2many.models.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product,Long>{

}
