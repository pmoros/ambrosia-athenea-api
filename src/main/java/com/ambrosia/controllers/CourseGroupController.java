package com.ambrosia.controllers;

import com.ambrosia.models.CourseGroup;
import com.ambrosia.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseGroupController {

    @Autowired
    private CourseGroupRepository courseGroupRepository;

    @PostMapping("/inscriptions/course-group")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourseGroup(@RequestBody CourseGroup courseGroup) {
        // TODO: check if courseGroup already exists
        this.courseGroupRepository.save(courseGroup);
    }

    // enpoint to get all course groups
    // public void getAllCourseGroups() {

    // }

}
