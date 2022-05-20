package com.jamesorban.worldinserbiastudentmanagementsystem;

import com.jamesorban.worldinserbiastudentmanagementsystem.model.Faculty;
import com.jamesorban.worldinserbiastudentmanagementsystem.repository.FacultyRepository;
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
public class FacultyTests {
    @Autowired
    private FacultyRepository repo;

    @Test
    @Rollback(value = false)
    public void testCreateFaculty(){
        Faculty faculty = new Faculty("Organizational Sciences", "Jove Ilica 154", 1 );
        Faculty savedFaculty = repo.save(faculty);

        assertNotNull(savedFaculty);
    }

    @Test
    public void testFindFacultyByNameExist(){
        String name = "Organizational Sciences";
        Faculty faculty = repo.findByName(name);
        assertThat(faculty.getName()).isEqualTo(name);
    }

    @Test
    public void testFindFacultyByNameNotExist(){
        String name = "Electrical Engineering";
        Faculty faculty = repo.findByName(name);
        assertNull(faculty);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateFaculty(){
        String facultyName = "Electrical Engineering";
        Faculty faculty = new Faculty(facultyName,  "Milanovic 1554", 2);
        faculty.setId(1);

        repo.save(faculty);

        Faculty updatedFaculty = repo.findByName(facultyName);

        assertThat(updatedFaculty.getName()).isEqualTo(facultyName);
    }

    @Test
    public void testListFaculties() {
        List<Faculty> faculties = repo.findAll();

        for (Faculty faculty : faculties) {
            System.out.println(faculty);
        }

        assertThat(faculties).isNotEqualTo(0);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteFaculty(){
        long id = 1;
        boolean isExistBeforeDelete = repo.findById(id).isPresent();
        repo.deleteById(id);

        boolean notExistAfterDelete = repo.findById(id).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }
}
