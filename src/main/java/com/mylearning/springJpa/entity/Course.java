package com.mylearning.springJpa.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.mylearning.springJpa.config.JsonViewProfiles;

@Entity
@Table(name = "course")

public class Course {
	@Id
	@SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "name", nullable = false, columnDefinition = "TEXT")
	@JsonView(JsonViewProfiles.Public.class)
	private String name;
	@Column(name = "department", nullable = false, columnDefinition = "TEXT")
	@JsonView(JsonViewProfiles.Public.class)
	private String department;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "student_course_map", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
	@JsonBackReference
	private Set<Student> studentList;

	public Course(String name, String department) {
		this.name = name;
		this.department = department;
	}

	public Course() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(Set<Student> studentList) {
		this.studentList = studentList;
	}

	public void addStudents(Student student) {
		if(studentList == null) {
			studentList = new HashSet<Student>();
		}
		studentList.add(student);
	}

	public void removeStudent(Student student) {
		studentList.remove(student);
	}
	
	public void removeStudents() {
		for(Student student:new HashSet<>(studentList)) {
			removeStudent(student);
		}
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", department=" + department + ", studentList=" + studentList + "]";
	}

}
