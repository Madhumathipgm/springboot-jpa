package com.mylearning.springJpa.service;

import java.util.List;



import com.mylearning.springJpa.entity.Student;
import com.mylearning.springJpa.exception.EntityNotFoundException;




public interface StudentService {

	public Student saveStudent(Student student);

	public List<Student> fetchStudentList();

	public Student fetchStudentByid(Long id) throws EntityNotFoundException;

	public void deleteStudentById(Long id) throws EntityNotFoundException, Exception;

	public Student updateStudent(Long id, Student student) throws EntityNotFoundException;

	


}
