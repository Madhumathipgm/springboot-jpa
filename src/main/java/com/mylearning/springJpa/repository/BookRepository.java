package com.mylearning.springJpa.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mylearning.springJpa.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	
	@Query(value  = "select * from book where student_id=:id", nativeQuery = true)
	public List<Book> findBook(@Param("id") Long id);

}
