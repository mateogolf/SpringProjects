package com.mattr.books.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mattr.books.models.Book;
import com.mattr.books.services.BookService;

@Controller
public class Books {
	private final BookService bookService;
	
	public Books(BookService bookService) {
		this.bookService = bookService;
	}
	
    @RequestMapping("/books")
    public String books(Model model) {
    	ArrayList<Book> books = bookService.allBooks();
        model.addAttribute("books", books);
        return "books";
    }
    
    @RequestMapping("/books/{index}")
    public String findBookByIndex(Model model, @PathVariable("index") Long index) {
//        Book book = bookService.findBookByIndex(index);
        model.addAttribute("id", index);
        model.addAttribute("book", bookService.findBookByIndex(index));
        return "showBook";
    }
    
    @RequestMapping("/books/new")
    public String newBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        return "newBook";
    }
    @PostMapping("/books/create")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "newBook";
        }else{
        	bookService.addBook(book);
            return "redirect:/books";
        }
    }
    
    @RequestMapping("/books/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBookByIndex(id);
        if (book != null){
            model.addAttribute("book", book);
            return "editBook";
        }else{
            return "redirect:/books";
        }
    }
    @PostMapping("/books/edit/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "editBook";
        }else{
            bookService.updateBook(id, book);
            return "redirect:/books";
        }
    }
    
    @RequestMapping(value="/books/delete/{id}")
    public String destroyBook(@PathVariable("id") Long id) {
        bookService.destroyBook(id);
        return "redirect:/books";
    }
}