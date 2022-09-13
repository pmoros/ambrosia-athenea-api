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

    @PostMapping("/inscriptions/course-groups")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseGroup createCourseGroup(@RequestBody CourseGroup courseGroup) {
        // TODO: check if courseGroup already exists
        courseGroup = this.courseGroupRepository.save(courseGroup);
        return courseGroup;
    }

    @PostMapping("/inscriptions/course-groups/professor")
    @ResponseStatus(HttpStatus.OK)
    public void setProfessor(@RequestParam String courseCode, @RequestParam String courseGroupCode,
            @RequestParam String professorCode) {
        List<CourseGroup> courseGroups = this.courseGroupRepository.findByCourseCodeAndCourseGroupCode(courseCode,
                courseGroupCode);
        Professor professor = this.professorRepository.findByProfessorCode(professorCode);
        CourseGroup courseGroup = courseGroups.get(0);
        courseGroup.setProfessor(professor);
        this.courseGroupRepository.save(courseGroup);
    }

}
