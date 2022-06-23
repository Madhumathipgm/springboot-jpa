package com.mylearning.springJpa.repository;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mylearning.springJpa.entity.Course;
import com.mylearning.springJpa.entity.Student;

@SpringBootTest
class CourseRepositoryTest {
	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void saveCourseWithStudent() {

		Course course = new Course("automotive", "CSE");
		Set<Student>courses=new HashSet<>();
		Student student = new Student("biji", "samy", "biji"
				+ "@gmail.com", 25);
		Student student1 = new Student("cat", "samy", "cat"
				+ "@gmail.com", 25);
		courses.add(student);
		courses.add(student1);
		course.setStudentList(courses);
		courseRepository.save(course);

	}

}
