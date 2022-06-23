package com.mylearning.springJpa.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mylearning.springJpa.entity.Course;
import com.mylearning.springJpa.exception.ElementAlreadyExistException;
import com.mylearning.springJpa.exception.EntityNotFoundException;
import com.mylearning.springJpa.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping("/course")
	public Course saveCourses(@RequestBody Course courses) throws EntityNotFoundException, ElementAlreadyExistException {
		try {
			return courseService.saveCourse(courses);
		}
		catch(DataIntegrityViolationException e) {

			throw new ElementAlreadyExistException("email already exist");

		}

	}

	@GetMapping("/courses")
	public List<Course> fetchStudentList() {
		return courseService.fetchCourseList();
	}

	@GetMapping("/course/{id_student}")
	public List<Course> fetchstudentById(@PathVariable Long id_student) throws EntityNotFoundException {
		return courseService.fetchCourseByStudentId(id_student);
	}

	@PutMapping("/course/{id}")
	public Course updateCourseById(@PathVariable Long id, @RequestBody Course course) throws EntityNotFoundException, ElementAlreadyExistException {
		return courseService.updateCourseByCourseId(id, course);
	}

	@PutMapping("/course/student/{id_student}")
	public List<Course> updateCourseByStudentId(@PathVariable Long id_student, @RequestBody List<Course> courses) throws Exception {
		return courseService.updateCourseForStudentId(id_student, courses);
	}

	@DeleteMapping("/course/{id}")
	public String deleteCourseBystudentId(@PathVariable Long id) throws EntityNotFoundException {
		courseService.deleteCourseByCourseId(id);
		return "course deleted successfully";
	}

	@DeleteMapping("/course/student/{id}")
	public String deleteCourseByCourseId(@PathVariable Long id) throws EntityNotFoundException {
		courseService.deleteCourseByStudentId(id);
		return "course deleted successfully";
	}

}
