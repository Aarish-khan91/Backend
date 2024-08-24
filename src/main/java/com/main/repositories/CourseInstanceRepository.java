package com.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entities.CourseInstances;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstances, Long> {

    List<CourseInstances> findByYearAndSemester(int year, int semester);
}