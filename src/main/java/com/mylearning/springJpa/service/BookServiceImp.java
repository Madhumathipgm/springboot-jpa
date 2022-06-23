package com.mylearning.springJpa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Optional;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylearning.springJpa.entity.Book;
import com.mylearning.springJpa.entity.Student;
import com.mylearning.springJpa.exception.ElementAlreadyExistException;
import com.mylearning.springJpa.exception.EntityNotFoundException;
import com.mylearning.springJpa.repository.BookRepository;
import com.mylearning.springJpa.repository.StudentRepository;



@Service
public class BookServiceImp implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional
	public Book saveBook(Book book) throws Exception {
		
		
			Student student = book.getStudent();
			if(student == null) {
				throw new EntityNotFoundException("Student not found");
			}			
			Book bookInstance = new Book();
			bookInstance.setBookName(book.getBookName());
			bookInstance.setCreatedAt(book.getCreatedAt());
			bookInstance.setStudent(student);
		
		
		
		return bookRepository.save(bookInstance);

	}

	@Override
	public List<Book> fetchBookList() {

		return bookRepository.findAll();
	}

	@Override
	public List<Book> fetchBookByStudentId(Long id) throws EntityNotFoundException {
		Optional<Student> student = studentRepository.findById(id);
		if(student.isEmpty()) {
			throw new EntityNotFoundException("student not found");
		}
		List<Book> bookList = student.get().getBookList();
		if(bookList.isEmpty()) {
			throw new EntityNotFoundException("Books not found for given student id");
		}
		return student.get().getBookList();
	}

	@Override
	public void deleteBookByBookId(Long id) throws EntityNotFoundException {
		Optional<Book> book = bookRepository.findById(id);
		if(book.isEmpty()) {
			throw new EntityNotFoundException("book not found for given id");
		}
		bookRepository.deleteById(id);

	}

	@Override
	public void deleteBookByStudentId(Long id) throws EntityNotFoundException {
		Optional<Student> student = studentRepository.findById(id);

		if(student.isEmpty()) {
			throw new EntityNotFoundException("student not found for given id");
		}
		List<Book> books = student.get().getBookList();
		if(books.isEmpty()) {
			throw new EntityNotFoundException("book not found for given id");
		}
		for(Book b:books) {
			bookRepository.delete(b);
		}
        
	}

	@Override
	@Transactional
	public Book updateBookByBookId(Long id, Book book) throws EntityNotFoundException, ElementAlreadyExistException {
		Optional<Book> bookOptional = bookRepository.findById(id);
		if(!bookOptional.isPresent()) {
			throw new EntityNotFoundException("book not found for given id");
		}
		Book bookDb = bookOptional.get();
		Student studentDb = bookDb.getStudent();
		List<Book>bookListfromDb=bookRepository.findBook(studentDb.getId());
		if(bookListfromDb!=null) {
			for(Book b:bookListfromDb) {
				System.out.println(b);
				if(b.getBookName().equalsIgnoreCase(book.getBookName())&&b.getCreatedAt().getTime()==book.getCreatedAt().getTime()) {
					throw new ElementAlreadyExistException("This book already exist for  student id "+studentDb.getId());
				}
			}
		}
		if(book.getStudent() != null) {
			if(book.getStudent().getFirstName() != null && !book.getStudent().getFirstName().equalsIgnoreCase("")) {
				studentDb.setFirstName(book.getStudent().getFirstName());
			}
			if(book.getStudent().getLastName() != null && !book.getStudent().getLastName().equalsIgnoreCase("")) {
				studentDb.setLastName(book.getStudent().getLastName());
			}
			if(book.getStudent().getEmail() != null && !book.getStudent().getEmail().equalsIgnoreCase("")) {
				studentDb.setEmail(book.getStudent().getEmail());
			}
			if(book.getStudent().getAge() != null) {
				studentDb.setAge(book.getStudent().getAge());
			}

		}

		if(book.getBookName() != null && !book.getBookName().equalsIgnoreCase("")) {
			bookDb.setBookName(book.getBookName());
		}
		if(book.getCreatedAt() != null) {
			bookDb.setCreatedAt(book.getCreatedAt());
		}
		bookDb.setStudent(studentDb);
		return bookRepository.save(bookDb);
	}

	@Override
	@Transactional
	public List<Book> addBookListforStudentId(Long id, List<Book> books) throws EntityNotFoundException, ElementAlreadyExistException {
		Optional<Student> student = studentRepository.findById(id);
		if(student.isEmpty()) {
			throw new EntityNotFoundException("student not found for given id");
		}
		Student studentDb = student.get();
		List<Book>updatedBookList=new ArrayList<>();
		List<Book> bookListDb = studentDb.getBookList();
		for(Book b:  new HashSet<>(books)) {
			for(Book bookDb:bookListDb) {
				if(b.getBookName().equalsIgnoreCase(bookDb.getBookName())&& b.getCreatedAt().getTime()==bookDb.getCreatedAt().getTime()) {
					throw new ElementAlreadyExistException("This book "+b.getBookName()+" already exist for  student id "+id);
				}
			}
			Book book = new Book();
			book.setBookName(b.getBookName());
			book.setCreatedAt(b.getCreatedAt());
			book.setStudent(studentDb);
			updatedBookList.add(book);
		}	
		updatedBookList.addAll(bookListDb);

		return bookRepository.saveAll(updatedBookList);
	}

}
