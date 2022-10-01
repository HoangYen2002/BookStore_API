package com.book.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.Book;
import com.book.entity.Order;
import com.book.entity.OrderDetail;
import com.book.exception.BookNotFoundException;
import com.book.service.BookService;
import com.book.service.OrderService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {
	
	@Autowired
	@Qualifier("bookServiceImpl")
	private BookService bookService;

	@GetMapping("/books")
	public List<Book> getAllBooks(@RequestParam(value = "text", required = false) String text) {
		List<Book> books = (text == null) ? bookService.getBooks() : bookService.getBooksContainingText(text);
		return books;
	}

	@PostMapping("/books")
	public Book createBook(@Valid @RequestBody Book bookRequest) {
		Book newBook = bookService.saveBook(bookRequest);
		return newBook;
	}

	@DeleteMapping("/books/{isbn}")
	public void deleteBook(@PathVariable String isbn) {
		Book book = bookService.findByIsbn(isbn);
		if (book == null) {
			throw new BookNotFoundException(String.format("Book with isbn %s not found", isbn));
		}
		bookService.deleteBook(book);
	}
	
}