package com.mylearning.springJpa.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockitoSession;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sound.midi.VoiceStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearning.springJpa.entity.Student;
import com.mylearning.springJpa.exception.EntityNotFoundException;
import com.mylearning.springJpa.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	private Student student;

	@BeforeEach
	void setUp() {
		student=new Student("shanu","Shanmuganathan","ss@gmail.com",33);
	}
	
	@Test
	void saveStudent() throws Exception {
		Student inputStudent =new Student("shanu","Shanmuganathan","ss@gmail.com",33);
		Mockito.when(studentService.saveStudent(inputStudent)).thenReturn(student);
		mockMvc.perform(MockMvcRequestBuilders.post("/student").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\": 1,\r\n"
						+ "    \"firstName\": \"shanu\",\r\n"
						+ "    \"lastName\": \"Shanmuganathan\",\r\n"
						+ "    \"email\": \"ss@gmail.com\",\r\n"
						+ "    \"age\": 33}")).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	void fetchStudentById() throws Exception {
		  Mockito.when(studentService.fetchStudentByid(1L)).thenReturn(student);
		  mockMvc.perform(MockMvcRequestBuilders.get("/student/1").contentType(MediaType.APPLICATION_JSON))
		          .andExpect(jsonPath("$.firstName").value(student.getFirstName()));
	}
	
	@Test
	void updateStudent() throws Exception {
		Student inputStudent =new Student("shanu","Shanmuganathan","ss@gmail.com",33);
		Mockito.when(studentService.fetchStudentByid(1L)).thenReturn(student);
		Mockito.when(studentService.saveStudent(inputStudent)).thenReturn(student);
		mockMvc.perform(MockMvcRequestBuilders.put("/student/{id}",2L).content(asJsonString(new Student(2L,"shanu","palanisamy","shanu.3e@gmail.com",33))).contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		           .andExpect(jsonPath("$.age", is(33)));
		 
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	 

}
