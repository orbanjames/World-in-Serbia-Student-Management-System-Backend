package com.jamesorban.worldinserbiastudentmanagementsystem;
import com.jamesorban.worldinserbiastudentmanagementsystem.model.Course;
import com.jamesorban.worldinserbiastudentmanagementsystem.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseTests {
    @Autowired
    private CourseRepository repo;

    @Test
    @Rollback(value = false)
    public void testCreateCourse(){
        Course course = new Course("Advanced Software Engineering", "SWE501", 1 );
        Course savedCourse = repo.save(course);

        assertNotNull(savedCourse);
    }

    @Test
    public void testFindCourseByTitleExist(){
        String title = "Advanced Software Engineering";
        Course course = repo.findByTitle(title);
        assertThat(course.getTitle()).isEqualTo(title);
    }

    @Test
    public void testFindCourseByNameNotExist(){
        String title = "Electrical Engineering";
        Course course = repo.findByTitle(title);
        assertNull(course);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateCourse(){
        String courseTitle = "Electrical Engineering";
        Course course = new Course(courseTitle,  "EEE554", 2);
        course.setId(1);

        repo.save(course);

        Course updatedCourse = repo.findByTitle(courseTitle);

        assertThat(updatedCourse.getTitle()).isEqualTo(courseTitle);
    }

    @Test
    public void testListCourses() {
        List<Course> courses = repo.findAll();

        for (Course course : courses) {
            System.out.println(course);
        }

        assertThat(courses).isNotEqualTo(0);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteCourse(){
        long id = 1;
        boolean isExistBeforeDelete = repo.findById(id).isPresent();
        repo.deleteById(id);

        boolean notExistAfterDelete = repo.findById(id).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }
}

