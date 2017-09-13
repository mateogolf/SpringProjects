package com.mattr.books.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattr.books.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
