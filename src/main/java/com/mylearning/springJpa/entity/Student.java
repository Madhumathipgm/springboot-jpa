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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mylearning.springJpa.config.JsonViewProfiles;

@Entity(name = "Student")
@Table(name = "student", uniqueConstraints = { @UniqueConstraint(name = "student_email_unique", columnNames = "email") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	@Size(min = 2, message = "firstname minimum length should be 2")
	@Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
	private String firstName;
	@Length(min = 2, message = "lastname minimun length should be 2")
	@Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
	private String lastName;
	//@NotEmpty(message = "email should not be empty")
	//@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	@Column(name = "email", nullable = false, columnDefinition = "TEXT")
	private String email;
	//@NotEmpty(message = "age should not be empty")
	@Column(name = "age", nullable = false)
	private Integer age;

	@OneToOne(mappedBy = "student", targetEntity = StudentIdCard.class,cascade = CascadeType.ALL)
	@JsonManagedReference
	private StudentIdCard studentIdCard;

	@OneToMany(mappedBy = "student", cascade  = {CascadeType.ALL})
	@JsonManagedReference
	private List<Book> bookList;

	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "studentList", fetch = FetchType.LAZY)
	//@JsonManagedReference
	private Set<Course> courseList;

	public Student() {
	}

	public Student(Long id, String firstName, String lastName, String email, Integer age) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}

	public Student(String firstName, String lastName, String email, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public StudentIdCard getStudentIdCard() {
		return studentIdCard;
	}

	public void setStudentIdCard(StudentIdCard studentIdCard) {
		this.studentIdCard = studentIdCard;
	}

	public Set<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(Set<Course> courseList) {
		this.courseList = courseList;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", age=" + age + "]";
	}

	public void addBook(Book book) {
		if(bookList == null) {
			bookList = new ArrayList<>();
		}
		bookList.add(book);
	}

	public void deleteBooks() {
		for(Book b: bookList) {
			deleteBook(b);
		}
	}

	private void deleteBook(Book b) {
		bookList.remove(b);

	}

	public void addCourse(Course course) {
		if(courseList == null) {
			courseList = new HashSet<>();
		}
		courseList.add(course);
	}

	public void deleteCourse(Course course) {
		courseList.remove(course);

	}

	public void deleteCourses() {
		for(Course c: new HashSet<>(courseList)) {
			deleteCourse(c);
		}
	}

}
