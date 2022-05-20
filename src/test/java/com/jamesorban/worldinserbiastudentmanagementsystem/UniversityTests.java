package com.jamesorban.worldinserbiastudentmanagementsystem;

import com.jamesorban.worldinserbiastudentmanagementsystem.model.University;
import com.jamesorban.worldinserbiastudentmanagementsystem.repository.UniversityRepository;
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
public class UniversityTests {
    @Autowired
    private UniversityRepository repo;

    @Test
    @Rollback(value = false)
    public void testCreateUniversity(){
        University university = new University("University of Belgrade", "Belgrade", "Belgrade" );
        University savedUniversity = repo.save(university);

        assertNotNull(savedUniversity);
    }

    @Test
    public void testFindUniversityByNameExist(){
        String name = "University of Belgrade";
        University university = repo.findByName(name);
        assertThat(university.getName()).isEqualTo(name);
    }

    @Test
    public void testFindUniversityByNameNotExist(){
        String name = "University of Nis";
        University university = repo.findByName(name);
        assertNull(university);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateUniversity(){
        String universityName = "University of Nis";
        University university = new University(universityName,  "Belgrade", "Belgrade");
        university.setId(1);

        repo.save(university);

        University updatedUniversity = repo.findByName(universityName);

        assertThat(updatedUniversity.getName()).isEqualTo(universityName);
    }

    @Test
    public void testListUniversities() {
        List<University> universities = repo.findAll();

        for (University university : universities) {
            System.out.println(university);
        }

        assertThat(universities).isNotEqualTo(0);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteUniversity(){
        long id = 1;
        boolean isExistBeforeDelete = repo.findById(id).isPresent();
        repo.deleteById(id);

        boolean notExistAfterDelete = repo.findById(id).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }
}
