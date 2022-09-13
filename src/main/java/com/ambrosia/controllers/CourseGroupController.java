package com.ambrosia.controllers;

import com.ambrosia.models.CourseGroup;
import com.ambrosia.models.Professor;
import com.ambrosia.models.Schedule;
import com.ambrosia.repositories.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseGroupController {
    // TODO: Add PUT and DELETE methods
    @Autowired
    private CourseGroupRepository courseGroupRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @PostMapping("/inscriptions/course-groups")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourseGroup(@RequestBody CourseGroup courseGroup) {
        // TODO: check if courseGroup already exists
        courseGroup = this.courseGroupRepository.save(courseGroup);
    }

    @PostMapping("/inscriptions/course-groups/professor")
    @ResponseStatus(HttpStatus.OK)
    public void setProfessor(@RequestParam String courseCode, @RequestParam String courseGroupCode,
            @RequestParam String professorCode) {
        List<CourseGroup> courseGroups = this.courseGroupRepository.findByCourseCodeAndCourseGroupCode(courseCode,
                courseGroupCode);
        List<Professor> professors = this.professorRepository.findByProfessorCode(professorCode);
        Professor professor = professors.get(0);
        CourseGroup courseGroup = courseGroups.get(0);
        courseGroup.setProfessor(professor);
        this.courseGroupRepository.save(courseGroup);
    }

    @PostMapping("/inscriptions/course-groups/schedules")
    @ResponseStatus(HttpStatus.OK)
    public void setSchedules(@RequestParam String courseCode, @RequestParam String courseGroupCode,
            @RequestBody List<Schedule> schedules) {
        // ! This saves the schedules in the database every time

        List<CourseGroup> courseGroups = this.courseGroupRepository.findByCourseCodeAndCourseGroupCode(courseCode,
                courseGroupCode);
        CourseGroup courseGroup = courseGroups.get(0);

        for (Schedule schedule : schedules) {
            schedule.setCourseGroup(courseGroup);
            this.scheduleRepository.save(schedule);
        }

        courseGroup.setSchedules(schedules);
        this.courseGroupRepository.save(courseGroup);
    }

    @GetMapping("/inscriptions/course-groups")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<CourseGroup> getCourseGroups(@RequestParam String courseCode,
            @RequestParam String courseGroupCode, @RequestParam String courseName) {
        List<CourseGroup> groups = this.courseGroupRepository.findAllByCourseCodeOrCourseGroupCodeOrCourseName(
                courseCode, courseGroupCode,
                courseName);

        return groups;
    }

}
