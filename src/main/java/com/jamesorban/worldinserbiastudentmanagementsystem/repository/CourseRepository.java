package com.jamesorban.worldinserbiastudentmanagementsystem.repository;

import com.jamesorban.worldinserbiastudentmanagementsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    public Course findByTitle(String title);
}
