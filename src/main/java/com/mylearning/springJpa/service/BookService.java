package com.mylearning.springJpa.service;

import java.util.Date;
import java.util.List;



import com.mylearning.springJpa.entity.Book;
import com.mylearning.springJpa.exception.ElementAlreadyExistException;
import com.mylearning.springJpa.exception.EntityNotFoundException;


public interface BookService {

	public Book saveBook(Book book) throws EntityNotFoundException, Exception;

	public List<Book> fetchBookList();

	public List<Book> fetchBookByStudentId(Long id) throws EntityNotFoundException;

	public void deleteBookByBookId(Long id) throws EntityNotFoundException ;
	
	public void deleteBookByStudentId(Long id) throws EntityNotFoundException ;
	

	public Book updateBookByBookId(Long id,Book book)throws EntityNotFoundException, ElementAlreadyExistException;
	
	public  List<Book> addBookListforStudentId(Long id, List<Book> books) throws EntityNotFoundException, ElementAlreadyExistException;

}
