package com.jamesorban.worldinserbiastudentmanagementsystem.repository;

import com.jamesorban.worldinserbiastudentmanagementsystem.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    public University findByName(String name);
}