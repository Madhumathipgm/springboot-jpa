package com.mylearning.springJpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.mylearning.springJpa.entity.Book;
import com.mylearning.springJpa.entity.Student;



@DataJpaTest
class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() {
		Student student=new Student("Madhu","Rathinasamy","madhu@gmail.com",32);
		entityManager.persist(student);
	}
	@Test
	public void whenFindById_thenReturnStudentName() {
		Student student=studentRepository.findById(1L).get();
		assertEquals(student.getLastName(), "Rathinasamy");
	}
	
	@Test
	public void printStudentByEmailAddresss() {
		Student student=studentRepository.getStudentByEmailAddress("madhu@gmail.com");
		assertEquals(student.getEmail(), "madhu@gmail.com");
	}
	
	@Test
	public void printStudentByAge() {
		Student student=studentRepository.getStudentByAge(32);
		assertEquals(student.getAge(), 32);
	}
	
	@Test
	public void updateStudentNameByEmail() {
		studentRepository.updateStudentNameByEmail("madhumathi", "madhu@gmail.com");
		
	}
	@Test
	public void saveBook() {
		List<Book>bookList=new ArrayList<>();
		bookList.add(new Book("java",new Date()));
		bookList.add(new Book("python",new Date()));
		Student student=new Student("priya","mohan","priya@gmail.com",22);
		student.setBookList(bookList);
		
	}
	

}
