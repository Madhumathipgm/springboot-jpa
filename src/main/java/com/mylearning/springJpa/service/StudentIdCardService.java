package com.mylearning.springJpa.service;

import java.util.List;

import com.mylearning.springJpa.entity.StudentIdCard;
import com.mylearning.springJpa.exception.EntityNotFoundException;



public interface StudentIdCardService {
	
	public StudentIdCard addstudentWithIdCard(StudentIdCard studentIdCard) throws EntityNotFoundException;

	public List<StudentIdCard> fetchStudentIdCardList();

	public StudentIdCard fetchStudentIdCardByCardno( String idCard) throws Exception;

	public void deleteStudentIdCardByStudentId( Long id) throws EntityNotFoundException;

	public StudentIdCard updateStudentIdCardNoByStudentId(Long id_student, StudentIdCard idCard) throws EntityNotFoundException;
	
	public StudentIdCard addStudentIdCardForExistingStudent(Long id,StudentIdCard idCard) throws EntityNotFoundException;
}
