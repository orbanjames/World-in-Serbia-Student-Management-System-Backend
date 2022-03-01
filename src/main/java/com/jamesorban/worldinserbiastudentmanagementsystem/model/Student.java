package com.jamesorban.worldinserbiastudentmanagementsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "programme_of_study")
    private String programmeOfStudy;

    @Column(name = "contact")
    private Integer contact;

    @Column(name = "year_of_award")
    private Integer yearOfAward;

    public Student() {
    }

    public Student(String firstName,
                   String lastName,
                   String email,
                   String country,
                   String programmeOfStudy,
                   Integer contact,
                   Integer yearOfAward) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.programmeOfStudy = programmeOfStudy;
        this.contact = contact;
        this.yearOfAward = yearOfAward;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProgrammeOfStudy() {
        return programmeOfStudy;
    }

    public void setProgrammeOfStudy(String programmeOfStudy) {
        this.programmeOfStudy = programmeOfStudy;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public Integer getYearOfAward() {
        return yearOfAward;
    }

    public void setYearOfAward(Integer yearOfAward) {
        this.yearOfAward = yearOfAward;
    }
}
