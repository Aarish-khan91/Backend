package com.main.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entities.Course;
import com.main.entities.CourseInstances;
import com.main.repositories.CourseRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/courses")
public class CourseController {
	@Autowired
	private CourseRepository courseRepository;

	@GetMapping("/{id}/instances")
	public ResponseEntity<List<CourseInstances>> getCourseInstances(@PathVariable Long id) {
		List<CourseInstances> instances = courseRepository.findAlloursesById(id);
		if (instances.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(instances);
	}

	@PostMapping
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@GetMapping
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
		Optional<Course> course = courseRepository.findById(id);
		return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
