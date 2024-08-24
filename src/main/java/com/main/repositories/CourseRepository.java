package com.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entities.Course;
import com.main.entities.CourseInstances;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c JOIN FETCH c.courseInstances")
	List<Course> findAllCoursesWithInstances();
	List<CourseInstances> findAlloursesById(Long id);
}