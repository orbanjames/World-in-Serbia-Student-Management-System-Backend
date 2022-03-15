package com.jamesorban.worldinserbiastudentmanagementsystem.repository;

import com.jamesorban.worldinserbiastudentmanagementsystem.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    public Faculty findByName(String name);
}
