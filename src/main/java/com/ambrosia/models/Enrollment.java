package com.ambrosia.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

// TODO: Use lombok for getters and setters
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String studentUsername;
    @Column
    private String academicHistoryCode;

    @ManyToMany
    @JoinTable(name = "enrollment_course_group", joinColumns = @JoinColumn(name = "enrollment_id"), inverseJoinColumns = @JoinColumn(name = "course_group_id"))
    List<CourseGroup> courseGroups;

    public Enrollment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getAcademicHistoryCode() {
        return academicHistoryCode;
    }

    public void setAcademicHistoryCode(String academicHistoryCode) {
        this.academicHistoryCode = academicHistoryCode;
    }

}
