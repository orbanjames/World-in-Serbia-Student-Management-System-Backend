package com.jamesorban.worldinserbiastudentmanagementsystem.controller;

import com.jamesorban.worldinserbiastudentmanagementsystem.exception.ResourceNotFoundException;
import com.jamesorban.worldinserbiastudentmanagementsystem.model.Course;
import com.jamesorban.worldinserbiastudentmanagementsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    // get all courses
    @GetMapping("/courses")
    public List<Course> getAllCourses(){

        return courseRepository.findAll();
    }

    //create courses rest api
    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {

        return courseRepository.save(course);
    }

    //get course rest api
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("course not exist with id:" + id));
        return ResponseEntity.ok(course);
    }
    //update course rest api
    @PutMapping("/course/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails){

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("course not exist with id:" + id));
        course.setTitle(courseDetails.getTitle());
        course.setFaculty_id(courseDetails.getFaculty_id());
        course.setCode(courseDetails.getCode());

        Course updatedCourse = courseRepository.save(course);
        return ResponseEntity.ok(updatedCourse);
    }
    //delete course rest api
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCourse(@PathVariable Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("course not exist with id:" + id));
        courseRepository.delete(course);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}

