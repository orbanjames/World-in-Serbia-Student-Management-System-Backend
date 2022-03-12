package com.jamesorban.worldinserbiastudentmanagementsystem.controller;

import com.jamesorban.worldinserbiastudentmanagementsystem.exception.ResourceNotFoundException;
import com.jamesorban.worldinserbiastudentmanagementsystem.model.Student;
import com.jamesorban.worldinserbiastudentmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // get all students
    @GetMapping("/students")
    public List<Student> getAllStudents(){

        return studentRepository.findAll();
    }

    //create student rest api
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {

        return studentRepository.save(student);
    }

    //get student rest api
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
                return ResponseEntity.ok(student);
    }
    //update student rest api
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setCountry(studentDetails.getCountry());
        student.setProgrammeOfStudy(studentDetails.getProgrammeOfStudy());
        student.setContact(studentDetails.getContact());
        student.setYearOfAward(studentDetails.getYearOfAward());

        Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }
    //delete student rest api
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
