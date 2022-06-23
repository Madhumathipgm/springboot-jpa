package com.mylearning.springJpa.controller;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.mylearning.springJpa.entity.StudentIdCard;



@WebMvcTest(StudentIdController.class)
class StudentIdControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private StudentIdCard studentIdCard;
	
	@BeforeEach
	void setUp() {
		studentIdCard=new StudentIdCard("CS123");
	}
	

}
