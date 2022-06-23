package com.mylearning.springJpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mylearning.springJpa.entity.Student;
import com.mylearning.springJpa.exception.ElementAlreadyExistException;
import com.mylearning.springJpa.exception.EntityNotFoundException;
import com.mylearning.springJpa.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	private final Logger logger=LoggerFactory.getLogger(StudentController.class);

	@PostMapping("/student")
	public Student saveStudent(@Valid @RequestBody Student student) throws ElementAlreadyExistException  {
		try {
			return studentService.saveStudent(student);
		}catch(DataIntegrityViolationException e) {
			throw new ElementAlreadyExistException(student.getEmail()+" already exist");
		}
		
	}

	@GetMapping("/students")
	public List<Student> fetchStudentList() {
		logger.info("inside fetchStudentList of StudentController");
		return studentService.fetchStudentList();
	}

	@GetMapping("/student/{id}")
	public Student fetchstudentById(@PathVariable Long id) throws EntityNotFoundException {
		return studentService.fetchStudentByid(id);
	}

	@PutMapping("/student/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student) throws  EntityNotFoundException, ElementAlreadyExistException {
		try {
			return studentService.updateStudent(id, student);
		}catch(DataIntegrityViolationException e) {
			throw new ElementAlreadyExistException(student.getEmail()+" already exist");
		}
		
	}

	@DeleteMapping("/student/{id}")
	public String deleteStudentById(@PathVariable Long id) throws Exception {
		studentService.deleteStudentById(id);
		return "student deleted successfully";
	}

	
	

	
	


}
