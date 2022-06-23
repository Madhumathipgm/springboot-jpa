package com.mylearning.springJpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "student_id_card",uniqueConstraints = { @UniqueConstraint(name = "student_idCard_unique", columnNames = "card_number") })
public class StudentIdCard {
	
	@Id
	@SequenceGenerator(name="studentIdCard_sequence",sequenceName = "studentIdCard_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "studentIdCard_sequence")
	@Column(name = "id",nullable = false)
	private Long id;
	@Column(name="card_number",nullable = false,columnDefinition = "TEXT")
	private String cardNo;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="student_id",referencedColumnName = "id")
	@JsonBackReference
	private Student student;
	

	
	public StudentIdCard(String cardNo) {
		
		this.cardNo = cardNo;
	}

	public StudentIdCard() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	

	
	

}
