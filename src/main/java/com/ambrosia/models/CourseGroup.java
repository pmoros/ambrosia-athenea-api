package com.ambrosia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CourseGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String courseGroupCode;
    @Column
    private String courseCode;
    @Column
    private String courseName;
    @Column
    private String courseDescription;
    @Column
    private String capacity;
    @Column
    private String taken;

    protected CourseGroup() {
    }

    public CourseGroup(String courseGroupCode, String courseCode, String courseName, String courseDescription,
            String capacity, String taken) {
        this.courseGroupCode = courseGroupCode;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.capacity = capacity;
        this.taken = taken;
    }

    @Override
    public String toString() {
        return String.format(
                "CourseGroup[id=%d, courseGroupCode='%s', courseCode='%s', courseName='%s', courseDescription='%s', capacity='%s', taken='%s']",
                id, courseGroupCode, courseCode, courseName, courseDescription, capacity, taken);
    }

    public Long getId() {
        return id;
    }

    public String getCourseGroupCode() {
        return courseGroupCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getTaken() {
        return taken;
    }

    public void setCourseGroupCode(String courseGroupCode) {
        this.courseGroupCode = courseGroupCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

}
