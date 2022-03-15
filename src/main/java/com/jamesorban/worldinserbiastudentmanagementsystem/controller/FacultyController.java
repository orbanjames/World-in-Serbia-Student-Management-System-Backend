package com.jamesorban.worldinserbiastudentmanagementsystem.controller;

import com.jamesorban.worldinserbiastudentmanagementsystem.exception.ResourceNotFoundException;
import com.jamesorban.worldinserbiastudentmanagementsystem.model.Faculty;
import com.jamesorban.worldinserbiastudentmanagementsystem.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class FacultyController {

    @Autowired
    private FacultyRepository facultyRepository;

    // get all faculties
    @GetMapping("/faculties")
    public List<Faculty> getAllFaculties(){

        return facultyRepository.findAll();
    }

    //create faculties rest api
    @PostMapping("/faculties")
    public Faculty createFaculty(@RequestBody Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    //get faculty rest api
    @GetMapping("/faculties/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("faculty not exist with id:" + id));
        return ResponseEntity.ok(faculty);
    }
    //update faculty rest api
    @PutMapping("/faculty/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty facultyDetails){

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("faculty not exist with id:" + id));
        faculty.setName(facultyDetails.getName());
        faculty.setUniversity_id((int) facultyDetails.getUniversity_id());
        faculty.setAddress(facultyDetails.getAddress());

        Faculty updatedFaculty = facultyRepository.save(faculty);
        return ResponseEntity.ok(updatedFaculty);
    }
    //delete faculty rest api
    @DeleteMapping("/faculties/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFaculty(@PathVariable Long id){
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("university not exist with id:" + id));
        facultyRepository.delete(faculty);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
