package com.mylearning.springJpa.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mylearning.springJpa.entity.Student;
import com.mylearning.springJpa.repository.StudentRepository;

@SpringBootTest
class StudentServiceTest {
	
	@Autowired
	private StudentService studentService;
	
	@MockBean
	private StudentRepository studentRepository;

	@BeforeEach
	void setup() {
	    Student student=new Student("Shanu","palanisamy","shanu@gamil.com",33);
	    Mockito.when(studentRepository.findByNameIgnoreCase("Shanu","Palanisamy")).thenReturn(student);
	}
	
	@Test
	public void saveStudent() {
		
	}
}
