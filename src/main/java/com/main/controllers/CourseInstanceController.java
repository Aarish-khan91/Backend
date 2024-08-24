package com.main.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entities.CourseInstances;
import com.main.repositories.CourseInstanceRepository;

@RestController
@RequestMapping("/api/instances")
public class CourseInstanceController {

	    @Autowired
	    private CourseInstanceRepository courseInstanceRepository;

	    @PostMapping
	    public CourseInstances createCourseInstance(@RequestBody CourseInstances instance) {
	        return courseInstanceRepository.save(instance);
	    }
	   
	    
	    @GetMapping
	    public List<CourseInstances> getAll() {
	        return courseInstanceRepository.findAll();
	    }

	    @GetMapping("/{year}/{semester}")
	    public List<CourseInstances> getInstancesByYearAndSemester(@PathVariable int year, @PathVariable int semester) {
	        return courseInstanceRepository.findByYearAndSemester(year, semester);
	    }

	    @GetMapping("/{year}/{semester}/{courseId}")
	    public ResponseEntity<CourseInstances> getInstanceDetail(@PathVariable int year, @PathVariable int semester, @PathVariable Long courseId) {
	        Optional<CourseInstances> instance = courseInstanceRepository.findById(courseId);
	        return instance.map(ResponseEntity::ok)
	                       .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{year}/{semester}/{courseId}")
	    public ResponseEntity<Void> deleteInstance(@PathVariable int year, @PathVariable int semester, @PathVariable Long courseId) {
	        if (courseInstanceRepository.existsById(courseId)) {
	            courseInstanceRepository.deleteById(courseId);
	            return ResponseEntity.ok().build();
	        }
	        return ResponseEntity.notFound().build();
	    }}
