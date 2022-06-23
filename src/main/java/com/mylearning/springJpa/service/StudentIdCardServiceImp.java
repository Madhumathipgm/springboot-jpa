package com.mylearning.springJpa.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mylearning.springJpa.entity.Student;
import com.mylearning.springJpa.entity.StudentIdCard;
import com.mylearning.springJpa.exception.EntityNotFoundException;
import com.mylearning.springJpa.repository.StudentIdCardRepository;
import com.mylearning.springJpa.repository.StudentRepository;

@Service
public class StudentIdCardServiceImp implements StudentIdCardService {

	@Autowired
	private StudentIdCardRepository idCardRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional
	public StudentIdCard addstudentWithIdCard(StudentIdCard studentIdCard) throws EntityNotFoundException {
		StudentIdCard sCard = new StudentIdCard();
		sCard.setCardNo(studentIdCard.getCardNo());
		sCard.setStudent(studentIdCard.getStudent());
		return idCardRepository.save(sCard);
	}

	@Override
	public List<StudentIdCard> fetchStudentIdCardList() {
		return idCardRepository.findAll();
	}

	@Override
	public StudentIdCard fetchStudentIdCardByCardno(String idCard) throws EntityNotFoundException {
		StudentIdCard studentIdCard = idCardRepository.getStudentIdCardByStudentcardno(idCard);
		if(studentIdCard == null) {
			throw new EntityNotFoundException("id card no not found");
		}
		return studentIdCard;

	}

	@Override
	public void deleteStudentIdCardByStudentId(Long id) throws EntityNotFoundException {
		Optional<Student> studentDb = studentRepository.findById(id);
		if(!studentDb.isPresent()) {
			throw new EntityNotFoundException("Student not found given id");
		}
		StudentIdCard studentIdCardDb = studentDb.get().getStudentIdCard();

		idCardRepository.deleteById(studentIdCardDb.getId());

	}

	@Override
	@Transactional
	public StudentIdCard updateStudentIdCardNoByStudentId(Long id_student, StudentIdCard idCard) throws EntityNotFoundException {
		Optional<Student> studentDb = studentRepository.findById(id_student);
		if(!studentDb.isPresent()) {
			throw new EntityNotFoundException("Student not found given id");
		}

		Student student = idCard.getStudent();
		if(student != null) {
			if(Objects.nonNull(student.getFirstName()) && !"".equalsIgnoreCase(student.getFirstName())) {
				student.setFirstName(student.getFirstName());
			}
			if(Objects.nonNull(student.getLastName()) && !"".equalsIgnoreCase(student.getLastName())) {
				student.setLastName(student.getLastName());
			}
			if(Objects.nonNull(student.getEmail()) && !"".equalsIgnoreCase(student.getEmail())) {
				student.setEmail(student.getEmail());
			}
			if(Objects.nonNull(student.getAge())) {
				student.setAge(student.getAge());
			}
		}
		StudentIdCard studentIdCardDb = studentDb.get().getStudentIdCard();
		if(Objects.nonNull(idCard.getCardNo()) && !"".equalsIgnoreCase(idCard.getCardNo())) {

			if(studentIdCardDb == null) {
				throw new EntityNotFoundException("StudentIdCard not found given student id");
			}
			else {
				studentIdCardDb.setCardNo(idCard.getCardNo());
			}
		}
		studentIdCardDb.setStudent(student);
		return idCardRepository.save(studentIdCardDb);
	}

	@Override
	@Transactional
	public StudentIdCard addStudentIdCardForExistingStudent(Long id, StudentIdCard idCard) throws EntityNotFoundException {
		Optional<Student> studentDb = studentRepository.findById(id);

		if(!studentDb.isPresent()) {
			throw new EntityNotFoundException("Student not found given id");
		}
		Student student = studentDb.get();
		StudentIdCard studentIdCardDb = studentDb.get().getStudentIdCard();

		if(studentIdCardDb == null) {
			studentIdCardDb = new StudentIdCard();
		}
		else {
			studentIdCardDb = student.getStudentIdCard();
		}

		if(Objects.nonNull(idCard.getCardNo()) && !"".equalsIgnoreCase(idCard.getCardNo())) {
			studentIdCardDb.setCardNo(idCard.getCardNo());
		}
		studentIdCardDb.setStudent(student);

		return idCardRepository.save(studentIdCardDb);

	}

}
