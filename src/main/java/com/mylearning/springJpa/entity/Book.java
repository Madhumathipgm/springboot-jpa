package com.mylearning.springJpa.entity;

import java.util.Date;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "book")
public class Book {
	@Id
	@SequenceGenerator(name = "book_sequence",sequenceName = "book_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_sequence")
	@Column(name = "id")
	private Long id;
    @Column(name = "book_name",nullable = false,columnDefinition = "TEXT")
	private String bookName;
    
    @Column(name="created_at",nullable = false,columnDefinition ="TIMESTAMP" )
    @JsonbDateFormat("dd/MM/yyyy")
	private Date createdAt;
    
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY
    )
    @JoinColumn(name="student_id",referencedColumnName = "id",nullable = false)
    @JsonBackReference
    private Student student;

	public Book( String bookName, Date createdAt) {
		this.bookName = bookName;
		this.createdAt = createdAt;
		
	}

	public Book() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(this==obj)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		Book book=(Book)obj;
		return (book.bookName==this.bookName && book.createdAt.getTime()==this.createdAt.getTime());
		
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", createdAt=" + createdAt + ", student=" + student + "]";
	}
	
	

}
