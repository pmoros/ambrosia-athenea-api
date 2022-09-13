package com.ambrosia.models;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String day;
    @Column
    private String building;
    @Column
    private String classroom;
    @Column
    private String timeOfStart;
    @Column
    private String timeOfEnd;

    @ManyToOne
    @JoinColumn(name = "course_group_id")
    private CourseGroup courseGroup;

    protected Schedule() {
    }

    public Schedule(String day, String building, String classroom, String timeOfStart, String timeOfEnd) {
        this.day = day;
        this.building = building;
        this.classroom = classroom;
        this.timeOfStart = timeOfStart;
        this.timeOfEnd = timeOfEnd;
    }

    @Override
    public String toString() {
        return String.format(
                "Schedule[id=%d, day='%s', building='%s', classroom='%s', timeOfStart='%s', timeOfEnd='%s']",
                id, day, building, classroom, timeOfStart, timeOfEnd);
    }

    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getBuilding() {
        return building;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getTimeOfStart() {
        return timeOfStart;
    }

    public String getTimeOfEnd() {
        return timeOfEnd;
    }

    public CourseGroup getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(CourseGroup courseGroup) {
        this.courseGroup = courseGroup;
    }

}
