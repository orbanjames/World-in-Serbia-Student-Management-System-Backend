package com.jamesorban.worldinserbiastudentmanagementsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "code")
    private String code;

    @Column(name = "faculty_id")
    private long faculty_id;

    public Course() {
    }

    public Course(String title,
                  String code,
                  long faculty_id) {
        this.title = title;
        this.code = code;
        this.faculty_id = faculty_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(long faculty_id) {
        this.faculty_id = faculty_id;
    }
}
