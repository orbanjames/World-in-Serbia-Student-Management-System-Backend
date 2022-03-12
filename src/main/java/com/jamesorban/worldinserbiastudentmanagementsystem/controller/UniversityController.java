package com.jamesorban.worldinserbiastudentmanagementsystem.controller;
import com.jamesorban.worldinserbiastudentmanagementsystem.exception.ResourceNotFoundException;
import com.jamesorban.worldinserbiastudentmanagementsystem.model.University;
import com.jamesorban.worldinserbiastudentmanagementsystem.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UniversityController {

    @Autowired
    private UniversityRepository universityRepository;

    // get all universities
    @GetMapping("/universities")
    public List<University> getAllUniversities(){

        return universityRepository.findAll();
    }

    //create university rest api
    @PostMapping("/universities")
    public University createUniversity(@RequestBody University university) {

        return universityRepository.save(university);
    }

    //get university rest api
    @GetMapping("/universities/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable Long id) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("university not exist with id:" + id));
        return ResponseEntity.ok(university);
    }
    //update university rest api
    @PutMapping("/universities/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @RequestBody University universityDetails){

        University university = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("university not exist with id:" + id));
        university.setName(universityDetails.getName());
        university.setLocation(universityDetails.getLocation());
        university.setAddress(universityDetails.getAddress());

        University updatedUniversity = universityRepository.save(university);
        return ResponseEntity.ok(updatedUniversity);
    }
    //delete university rest api
    @DeleteMapping("/universities/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUniversity(@PathVariable Long id){
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("university not exist with id:" + id));
        universityRepository.delete(university);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
