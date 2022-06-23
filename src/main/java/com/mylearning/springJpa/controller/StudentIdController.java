package com.mylearning.springJpa.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mylearning.springJpa.entity.StudentIdCard;
import com.mylearning.springJpa.exception.ElementAlreadyExistException;
import com.mylearning.springJpa.exception.EntityNotFoundException;
import com.mylearning.springJpa.exception.FindConstraints;
import com.mylearning.springJpa.service.StudentIdCardService;

@RestController
public class StudentIdController {

	@Autowired
	private StudentIdCardService studentIdCardService;

	@PostMapping("/studentIdCard")
	public StudentIdCard saveStudentIdCard(@RequestBody StudentIdCard studentIdCard) throws EntityNotFoundException, ElementAlreadyExistException {
		try {
			return studentIdCardService.addstudentWithIdCard(studentIdCard);
		}
		catch(DataIntegrityViolationException e) {
			if(FindConstraints.isExceptionUniqueConstrainerFor("email")) {
				throw new ElementAlreadyExistException("email already exist");
			}
			else if(FindConstraints.isExceptionUniqueConstrainerFor("card_number")) {
				throw new ElementAlreadyExistException(studentIdCard.getCardNo() + "or" + " already exist");
			}
			return null;

		}

	}

	@GetMapping("/studentIdCard")
	public List<StudentIdCard> fetchStudentIdCardList() {
		return studentIdCardService.fetchStudentIdCardList();
	}

	@GetMapping("/studentIdCard/{idCard}")
	public StudentIdCard fetchstudentById(@PathVariable String idCard) throws Exception {
		return studentIdCardService.fetchStudentIdCardByCardno(idCard);
	}

	@PutMapping("/studentIdCard/{id_student}")
	public StudentIdCard updateStudentIdcard(@PathVariable Long id_student, @RequestBody StudentIdCard studentIdCard) throws EntityNotFoundException, ElementAlreadyExistException {
		try {
			return studentIdCardService.updateStudentIdCardNoByStudentId(id_student, studentIdCard);
		}
		catch(DataIntegrityViolationException e) {
			throw new ElementAlreadyExistException(studentIdCard.getCardNo() + " already exist");
		}

	}

	@PutMapping("/studentIdCard/student/{id_student}")
	public StudentIdCard addStudentIdcardForStudent(@PathVariable Long id_student, @RequestBody StudentIdCard studentIdCard) throws EntityNotFoundException, ElementAlreadyExistException {
		try {
			return studentIdCardService.addStudentIdCardForExistingStudent(id_student, studentIdCard);
		}
		catch(DataIntegrityViolationException e) {
			throw new ElementAlreadyExistException(studentIdCard.getCardNo() + " already exist");
		}

	}

	@DeleteMapping("/studentIdCard/{id}")
	public String deleteStudentIdCardById(@PathVariable Long id) throws EntityNotFoundException {
		studentIdCardService.deleteStudentIdCardByStudentId(id);
		return "studentIdCard deleted successfully for given student id";
	}

}
