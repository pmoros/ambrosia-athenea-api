package com.ambrosia.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String professorCode;
    @Column
    private String name;
    @Column
    private String email;

    @OneToMany(mappedBy = "professor")
    private List<CourseGroup> courseGroups;

    protected Professor() {
    }

    public Professor(String professorCode, String name, String email) {
        this.professorCode = professorCode;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Professor[id=%d, professorCode='%s', name='%s', email='%s']",
                id, professorCode, name, email);
    }

    public Long getId() {
        return id;
    }

    public String getProfessorCode() {
        return professorCode;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<CourseGroup> getCourseGroups() {
        return courseGroups;
    }

}
