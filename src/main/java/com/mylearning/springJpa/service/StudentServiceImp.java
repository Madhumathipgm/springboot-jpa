package com.mylearning.springJpa.service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mylearning.springJpa.entity.Course;
import com.mylearning.springJpa.entity.Student;
import com.mylearning.springJpa.exception.CourseListNotEmpty;
import com.mylearning.springJpa.exception.EntityNotFoundException;
import com.mylearning.springJpa.repository.StudentRepository;



@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	

	@Override
	@Transactional
	public Student saveStudent(Student student) {

		return studentRepository.save(student);
	}

	@Override
	public List<Student> fetchStudentList() {
		return studentRepository.findAll();
	}

	@Override
	public Student fetchStudentByid(Long id) throws EntityNotFoundException {
		Optional<Student> student = studentRepository.findById(id);
		if(!student.isPresent()) {
			throw new EntityNotFoundException("Student not found for given id "+id);
		}
		return student.get();
	}

	@Override
	public void deleteStudentById(Long id) throws Exception {
		Optional<Student> student=studentRepository.findById(id);
		if(!student.isPresent()) {
			throw new EntityNotFoundException("Student not found for given id "+id);
		}
		Set<Course> courseDb=student.get().getCourseList();
		if(!courseDb.isEmpty()) {
			throw new CourseListNotEmpty("course list is not empty for this student");
		}
		student.get().deleteCourses();
		studentRepository.deleteById(id);		

	}

	@Override
	@Transactional
	public Student updateStudent(Long id, Student student) throws EntityNotFoundException {
		Optional<Student> studentDbOptional = studentRepository.findById(id);
		if(studentDbOptional.isEmpty()) {
			throw new EntityNotFoundException("student not found for given id "+id);
		}
		Student studentDb=studentDbOptional.get();
		if(Objects.nonNull(student.getFirstName()) && !"".equalsIgnoreCase(student.getFirstName())) {
			studentDb.setFirstName(student.getFirstName());
		}
		if(Objects.nonNull(student.getLastName()) && !"".equalsIgnoreCase(student.getLastName())) {
			studentDb.setLastName(student.getLastName());
		}
		if(Objects.nonNull(student.getEmail()) && !"".equalsIgnoreCase(student.getEmail())) {
			studentDb.setEmail(student.getEmail());
		}
		if(Objects.nonNull(student.getAge())) {
			studentDb.setAge(student.getAge());
		}

		return studentRepository.save(studentDb);
	}	
	
	


	
	
	

}
