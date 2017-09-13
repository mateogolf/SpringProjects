package com.mattr.books.services;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.mattr.books.models.Book;
import com.mattr.books.repositories.BookRepository;

@Service
public class BookService {
//	private ArrayList<Book> books = new ArrayList<Book>(Arrays.asList(new Book("Harry Potter and the Sorcerer's Stone", "A boy wizard saving the world", "english", 309),
//			new Book("The Great Gatsby", "The story primarily concerns the young and mysterious millionaire Jay Gatsby", "english", 180),
//            new Book("Moby Dick", "The saga of Captain Ahab", "english", 544),
//            new Book("Don Quixote", "Life of a retired country gentleman", "english", 150),
//            new Book("The Odyssey", "Ancient Greek epic poem", "english", 475)
//        ));//ArrayList used in original description
	
	private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    
	public ArrayList<Book> allBooks(){
		return (ArrayList<Book>)bookRepository.findAll();
		//return books;
	}
	public Book findBookByIndex(Long id) {
		return bookRepository.findOne(id);
//		if (index < books.size()){
//            return books.get(index);
//        }else{
//            return null;
//        }
	}
	
	public void addBook(Book book) {
		bookRepository.save(book);
		//books.add(book);
	}
	
	public void updateBook(Long id, Book book) {
		bookRepository.save(book);
//        if (id < books.size()){
//            books.set(id, book);
//        }
    }
	public void destroyBook(Long id) {
//		if (id < books.size()){
			bookRepository.delete(id);
			//books.remove(id);
//        }
	}
}
