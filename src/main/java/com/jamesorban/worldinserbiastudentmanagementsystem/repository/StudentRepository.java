package com.jamesorban.worldinserbiastudentmanagementsystem.repository;

import com.jamesorban.worldinserbiastudentmanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Student findByFirstName(String firstName);
}
