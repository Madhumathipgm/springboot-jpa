package com.mylearning.springJpa.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mylearning.springJpa.entity.Course;
import com.mylearning.springJpa.entity.Student;
import com.mylearning.springJpa.repository.CourseRepository;
import com.mylearning.springJpa.repository.StudentRepository;


@SpringBootTest
class CourseServiceTest {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@MockBean
	private StudentRepository studentRepository;
	@MockBean
	private CourseRepository courseRepository;

	@BeforeEach
	void setup() {
	    Student student=new Student("Shanu","palanisamy","shanu@gamil.com",33);
	    Mockito.when(studentRepository.findByNameIgnoreCase("Shanu","Palanisamy")).thenReturn(student);
	    Course course=new Course("Conatiner technlogie","CSE");
	}
	
	/**@Test
	@DisplayName("Get data based on the valid student name")
	public void whenValidStudentFirstName_thenStudentShouldFound() {
		String firstName="Shanu";
		String lastName="Palanisamy";
		Student foundStudent=studentService.fetchStudentByName(firstName,lastName);
		assertEquals(firstName, foundStudent.getFirstName());
	}**/

}
