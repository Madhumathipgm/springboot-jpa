package com.mylearning.springJpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mylearning.springJpa.entity.Book;
import com.mylearning.springJpa.exception.ElementAlreadyExistException;
import com.mylearning.springJpa.exception.EntityNotFoundException;
import com.mylearning.springJpa.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/book")
	public Book saveBook(@RequestBody Book book) throws Exception {
		try {
			return bookService.saveBook(book);
		}
		catch(DataIntegrityViolationException e) {

			throw new ElementAlreadyExistException("email already exist");

		}

	}

	@GetMapping("/books")
	public List<Book> fetchStudentList() {
		return bookService.fetchBookList();
	}

	@GetMapping("/book/{id}")
	public List<Book> fetchstudentById(@PathVariable Long id) throws EntityNotFoundException {
		return bookService.fetchBookByStudentId(id);
	}

	@PutMapping("/book/{id}")
	public Book updateBookByBookId(@PathVariable Long id, @RequestBody Book book) throws EntityNotFoundException, ElementAlreadyExistException {
		return bookService.updateBookByBookId(id, book);
	}

	@PutMapping("/book/student/{id}")
	public List<Book> addBookList4Student(@PathVariable Long id, @RequestBody List<Book> book) throws EntityNotFoundException, ElementAlreadyExistException {
		return bookService.addBookListforStudentId(id, book);
	}

	@DeleteMapping("/book/{id}")
	public String deleteStudentByBookId(@PathVariable Long id) throws EntityNotFoundException {
		bookService.deleteBookByBookId(id);
		return "book deleted successfully for given book id";
	}

	@DeleteMapping("/book/student/{id}")
	public String deleteStudentByStudentId(@PathVariable Long id) throws EntityNotFoundException {
		bookService.deleteBookByStudentId(id);
		return "books deleted successfully for given student id";
	}

}
