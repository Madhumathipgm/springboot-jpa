package com.mylearning.springJpa.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mylearning.springJpa.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	
	public Student findByFirstName(String firstName);
	
	@Query(value = "select * from Student where first_name=:firstName and last_name=:lastName",nativeQuery = true)
	public Student findByNameIgnoreCase( @Param("firstName") String firstName,@Param("lastName")  String lastName);
	
	
	
	//jpql
	@Query("select s from Student s where s.email=?1")
	public Student getStudentByEmailAddress(String emailId);
	
	
	@Query(value = "select * from Student where age=:age",nativeQuery = true)
	public Student getStudentByAge(@Param("age") Integer age);
	
	
	@Modifying
	@Transactional
	@Query(value ="update student set first_name=:firstName where email=:email",nativeQuery = true )
	public int updateStudentNameByEmail(@Param("firstName")  String firstName, @Param("email") String email);
}
