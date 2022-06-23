package com.mylearning.springJpa.service;


import java.util.List;

import com.mylearning.springJpa.entity.Course;
import com.mylearning.springJpa.exception.ElementAlreadyExistException;
import com.mylearning.springJpa.exception.EntityNotFoundException;


public interface CourseService {
	

	public Course saveCourse(Course courses) throws EntityNotFoundException;

	public List<Course> fetchCourseList();

	public List<Course> fetchCourseByStudentId(Long id) throws EntityNotFoundException;

	public void deleteCourseByCourseId(Long id) throws EntityNotFoundException;
	
	public void deleteCourseByStudentId(Long id) throws EntityNotFoundException;
	

	public Course updateCourseByCourseId(Long id, Course course) throws EntityNotFoundException, ElementAlreadyExistException;
	
	public List<Course> updateCourseForStudentId( Long id, List<Course> courseList) throws EntityNotFoundException, ElementAlreadyExistException, Exception;



}
