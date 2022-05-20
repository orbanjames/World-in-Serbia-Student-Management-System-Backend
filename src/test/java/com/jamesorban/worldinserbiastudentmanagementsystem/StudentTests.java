package com.jamesorban.worldinserbiastudentmanagementsystem;

import com.jamesorban.worldinserbiastudentmanagementsystem.model.Student;
import com.jamesorban.worldinserbiastudentmanagementsystem.repository.StudentRepository;
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
public class StudentTests {
    @Autowired
    private StudentRepository repo;

    @Test
    @Rollback(value = false)
    public void testCreateStudent(){
        Student student = new Student("Ngukenger", "Igulen", "ngukenger@gmail.com","Canada", "Bachelor", "09189427322", 2015 );
        Student savedStudent = repo.save(student);

        assertNotNull(savedStudent);
    }

    @Test
    public void testFindStudentByFirstNameExist(){
        String firstName = "Ngukenger";
        Student student = repo.findByFirstName(firstName);
        assertThat(student.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void testFindStudentByFirstNameNotExist(){
        String firstName = "Aondowase";
        Student student = repo.findByFirstName(firstName);
        assertNull(student);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateStudent(){
        String studentFirstName = "Aondowase";
        Student student = new Student(studentFirstName, "Orwase", "orwase10@gmail.com", "Zambia", "PhD","07058697322", 2010);
        student.setId(3);

        repo.save(student);

        Student updatedStudent = repo.findByFirstName(studentFirstName);

        assertThat(updatedStudent.getFirstName()).isEqualTo(studentFirstName);
    }

    @Test
    public void testListStudents() {
        List<Student> students = repo.findAll();

        for (Student student : students) {
            System.out.println(student);
        }

        assertThat(students).isNotEqualTo(0);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteStudent(){
        long id = 1;
        boolean isExistBeforeDelete = repo.findById(id).isPresent();
        repo.deleteById(id);

        boolean notExistAfterDelete = repo.findById(id).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }
}
