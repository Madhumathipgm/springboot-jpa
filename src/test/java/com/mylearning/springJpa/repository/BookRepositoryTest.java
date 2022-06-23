package com.mylearning.springJpa.repository;


import java.util.Date;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mylearning.springJpa.entity.Book;
import com.mylearning.springJpa.entity.Student;

@SpringBootTest
class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	
	
	@Test
	public void saveBook() {
		Book book=new Book("Go",new Date());
		
		Student student=new Student("Yamini","chandran","yamini1gmail.com",26);
		book.setStudent(student);
		
		bookRepository.save(book);
		
		
	}

}
