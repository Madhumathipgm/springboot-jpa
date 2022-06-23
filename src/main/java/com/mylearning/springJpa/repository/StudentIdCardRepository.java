package com.mylearning.springJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.mylearning.springJpa.entity.StudentIdCard;


@Repository
public interface StudentIdCardRepository extends JpaRepository<StudentIdCard,Long> {
	
	@Query(value = "select * from student_id_card where card_number=:idCard",nativeQuery = true)
	public StudentIdCard getStudentIdCardByStudentcardno(@Param("idCard")  String idCard);
	

}
