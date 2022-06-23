package com.mylearning.springJpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mylearning.springJpa.entity.Student;
import com.mylearning.springJpa.entity.StudentIdCard;

@SpringBootTest
class StudentIdCardRepositoryTest {
	
	@Autowired
	private StudentIdCardRepository studentIdCardRepository;

	@Test
	public void saveStudentIDCard() {
		Student student=new Student("madhu","rathinasamy","madhu@gmail.com",32);
		StudentIdCard studentIdCard=new StudentIdCard("123");
		studentIdCard.setStudent(student);
		studentIdCardRepository.save(studentIdCard);
	}

}
