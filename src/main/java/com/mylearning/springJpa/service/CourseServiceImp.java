package com.mylearning.springJpa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylearning.springJpa.entity.Course;
import com.mylearning.springJpa.entity.Student;
import com.mylearning.springJpa.exception.ElementAlreadyExistException;
import com.mylearning.springJpa.exception.EntityNotFoundException;
import com.mylearning.springJpa.repository.CourseRepository;
import com.mylearning.springJpa.repository.StudentRepository;

@Service
public class CourseServiceImp implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional
	public Course saveCourse(Course courseList) throws EntityNotFoundException {

		Course course = new Course();
		course.setName(courseList.getName());
		course.setDepartment(courseList.getDepartment());
		Set<Student> students = courseList.getStudentList();
		if(students == null) {
			throw new EntityNotFoundException("Student not found for this course");
		}
		for(Student s: students) {
			course.addStudents(s);
		}

		return courseRepository.save(course);

	}

	@Override
	public List<Course> fetchCourseList() {
		return courseRepository.findAll();
	}

	@Override
	public List<Course> fetchCourseByStudentId(Long id) throws EntityNotFoundException {
		Optional<Student> studentOptional = studentRepository.findById(id);
		if(!studentOptional.isPresent()) {
			throw new EntityNotFoundException("Student not found for this id");
		}
		Student student = studentOptional.get();
		Set<Course> courses = student.getCourseList();
		if(courses.isEmpty()) {
			throw new EntityNotFoundException("courses not found for this student id");
		}
		List<Course> courseList = new ArrayList<>();
		courseList.addAll(courses);
		return courseList;
	}

	@Override
	public void deleteCourseByCourseId(Long id) throws EntityNotFoundException {
		Optional<Course> course=courseRepository.findById(id);
		if(!course.isPresent()) {
			throw new EntityNotFoundException("courses not found for this  id");
		}
		Course courseDb=course.get();
		courseDb.removeStudents();
		courseRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public void deleteCourseByStudentId(Long id) throws EntityNotFoundException {
		Optional<Student> studentOptional = studentRepository.findById(id);
		if(!studentOptional.isPresent()) {
			throw new EntityNotFoundException("Student not found for this id");
		}
		Set<Course> courses = studentOptional.get().getCourseList();
		if(courses.isEmpty()) {
			throw new EntityNotFoundException("courses not found for this student id");
		}
		for(Course c:courses) {
			c.removeStudents();
			courseRepository.deleteById(c.getId());
		}
		
	}
	
	

	@Override
	@Transactional
	public Course updateCourseByCourseId(Long id, Course course) throws EntityNotFoundException, ElementAlreadyExistException {
		Optional<Course> coursesOptional = courseRepository.findById(id);
		if(!coursesOptional.isPresent()) {
			throw new EntityNotFoundException("courses not found for this student id");
		}
		Course courseDb = coursesOptional.get();
		Set<Student> studentDb=courseDb.getStudentList();
		for(Student s:studentDb) {
			Set<Course> courseSet= s.getCourseList();
			if(courseSet.isEmpty()) {
				continue;
			}
			for(Course c:courseSet) {
				if(c.getName().equalsIgnoreCase(course.getName())&&c.getDepartment().equalsIgnoreCase(course.getDepartment())) {
					throw new ElementAlreadyExistException("Course already exist for the student id "+s.getId());
				}
			}
			
		}
		if(course.getName() != null && !course.getName().equalsIgnoreCase("")) {
			courseDb.setName(course.getName());
		}
		if(course.getDepartment() != null && !course.getDepartment().equalsIgnoreCase("")) {
			courseDb.setDepartment(course.getDepartment());
		}

		return courseRepository.save(courseDb);
	}

	@Override
	@Transactional
	public List<Course> updateCourseForStudentId(Long id, List<Course> courseList) throws EntityNotFoundException, Exception {
		Optional<Student> studentOptional = studentRepository.findById(id);
		if(!studentOptional.isPresent()) {
			throw new EntityNotFoundException("Student not found for this id");
		}
		Student studentDb = studentOptional.get();
		List<Course> updatedCourseList = new ArrayList<>();
		Set<Course>courseListDb=studentDb.getCourseList();
		for(Course c: new HashSet<>(courseList)) {
			for(Course courseDb:courseListDb) {
				if(c.getName().equalsIgnoreCase(courseDb.getName())&&c.getDepartment().equalsIgnoreCase(courseDb.getDepartment())) {
					throw new ElementAlreadyExistException("This course "+c.getName()+" already exist for  student id "+id);
				}
			}
			Course course = new Course();
			course.setName(c.getName());
			course.setDepartment(c.getDepartment());
			course.addStudents(studentDb);
			updatedCourseList.add(course);
		}

		return courseRepository.saveAll(updatedCourseList);
	}

}
